package com.flyview.pharmmobile.data.barcode_reader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.os.Build
import android.os.Environment
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import com.flyview.core.barcode.data.BarcodeReaderData
import com.flyview.core.media.AppSound
import com.flyview.core.media.AudioPlayer
import com.flyview.pharmmobile.domain.barcode_reader.EmbeddedBarcodeReader
import com.zebra.adc.decoder.BarCodeReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.IOException
import java.util.Properties
import kotlin.coroutines.EmptyCoroutineContext

class BarcodeReaderT200(
    private val context: Context,
    private val audioPlayer: AudioPlayer
) : EmbeddedBarcodeReader {
    val use = MutableStateFlow(false)

    private val MANUFACTURER_NAME = "ATOL"
    private val DEVICE = "T200"

    private var bcr: BarCodeReader? = null
    private val intentTriggerDownReciver = ScannerTriggerDownBroadcastReceiver()
    private val intentTriggerUpReciver = ScannerTriggerUpBroadcastReceiver()
    private val intentFilterDown = IntentFilter("TRIGGERBUTTON_DOWN")
    private val intentFilterUp = IntentFilter("TRIGGERBUTTON_UP")
    private val state = MutableStateFlow(DecodeState.STATE_IDLE)
    private var surfaceTexture: SurfaceTexture? = null
    private val triggerState = MutableStateFlow(TriggerState.UP)
    private var vibrator: Vibrator? = null

    private var coroutineScanTask: Job? = null

    init {
        use.update {
            Build.MANUFACTURER.uppercase() == MANUFACTURER_NAME.uppercase()
                    && Build.DEVICE.uppercase() == DEVICE.uppercase()
        }

        // Получаем возможность вибрировать
        vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager)
                .defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

    private inner class ScannerTriggerDownBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(arg0: Context, arg1: Intent) {
            if (triggerState.value == TriggerState.DOWN) return
            triggerState.update { TriggerState.DOWN }

            coroutineScanTask?.cancel()
            try {
                bcr!!.startDecode()
            } catch (_: java.lang.Exception) {
                // Если была ошибка декодирования, то ничего не делаем..
            }
            state.value = DecodeState.STATE_DECODE
        }
    }

    inner class ScannerTriggerUpBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(arg0: Context, arg1: Intent) {
            if (triggerState.value == TriggerState.DOWN) {
                coroutineScanTask?.cancel()

                coroutineScanTask = CoroutineScope(EmptyCoroutineContext + Dispatchers.IO).launch {
                    delay(100)
                    bcr?.stopDecode()
                    triggerState.value = TriggerState.UP
                }
            }
        }
    }

    inner class ConfigurationIni {
        private val INI_PATH = "/Download/" + "ScanWedge.ini"
        private val INI_FALSE = "FALSE"
        private val INI_TRUE = "TRUE"
        private val properties = Properties()

        fun readFile() {
            try {
                properties.load(
                    FileInputStream(
                        Environment.getExternalStorageDirectory().toString().plus(INI_PATH)
                    )
                )
            } catch (e: IOException) {
                println("Configuration error: " + e.localizedMessage)
            }
        }

        fun ReadBoolean(key: String, DefaultValue: Boolean) =
            (properties.getProperty(key, if (DefaultValue) INI_TRUE else INI_FALSE) == INI_TRUE)

    }

    fun initBarCodeReader(
        cb: BarCodeReader.DecodeCallback, er: BarCodeReader.ErrorCallback,
        st: SurfaceTexture.OnFrameAvailableListener
    ) {
        try {

            val camNum = Camera.getNumberOfCameras()
            bcr = if (camNum == 0) BarCodeReader.open(0, context)
            else BarCodeReader.open(1, context)
            if (bcr == null) {
                return
            }

            bcr!!.setDecodeCallback(cb)
            bcr!!.setErrorCallback(er)

            // Set Orientation
            bcr!!.setParameter(765, 0) // For QC/MTK platforms
            bcr!!.setParameter(764, 5)
            bcr!!.setParameter(137, 5)

            // / Set Orientation
            bcr!!.setParameter(687, 4) // 4 - omnidirectional
            bcr!!.setParameter(588, 2) // enable Data Matrix by default
            bcr!!.setParameter(227, 1)
            bcr!!.setParameter(11, 1)
            bcr!!.setParameter(10, 1)
            bcr!!.setParameter(9, 1)
            bcr!!.setParameter(8610, 1)
            //bcr.setParameter(8611, 1);
            if (Build.VERSION.SDK_INT >= 28) {
                surfaceTexture = SurfaceTexture(5)
                surfaceTexture?.setOnFrameAvailableListener(st)
                bcr!!.setPreviewTexture(surfaceTexture)
            }

            context.registerReceiver(intentTriggerDownReciver, intentFilterDown)
            context.registerReceiver(intentTriggerUpReciver, intentFilterUp)
        } catch (e: Exception) {
            // При ошибке ничего не делаем..
        }
    }

    fun doneBarCodeReaderBefore() {
        try {
            context.unregisterReceiver(intentTriggerDownReciver)
        } catch (_: java.lang.Exception) {
        }

        try {
            context.unregisterReceiver(intentTriggerUpReciver)
        } catch (_: java.lang.Exception) {
        }
    }

    fun doneBarCodeReaderAfter() {
        bcr?.let {
            state.value = DecodeState.STATE_IDLE
            it.release()
            bcr = null
        }
    }

    private fun onBeep() {
        audioPlayer.play(AppSound.BEEP)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator?.vibrate(VibrationEffect.createOneShot(100, 10))
        } else {
            @Suppress("DEPRECATION")
            vibrator?.vibrate(100)
        }
    }

    override fun onDecodeComplete(
        symbology: Int,
        length: Int,
        data: ByteArray?,
        reader: BarCodeReader?
    ) {
        coroutineScanTask?.cancel()

        if (state.value == DecodeState.STATE_DECODE) {
            state.update { DecodeState.STATE_IDLE }
        }

        if (length > 0) {
            bcr?.stopDecode()
            val BeepThread = Thread(Runnable {
                onBeep()
                return@Runnable
            })
            BeepThread.start()

            val resultData = String(data!!).replace("\u0000", "").trim()

            if (resultData != "") {
                BarcodeReaderData.data.value = resultData
                BarcodeReaderData.data.value = ""
            }
        }
    }

    override fun onPauseBefore() {
        try {
            context.unregisterReceiver(intentTriggerDownReciver)
        } catch (exception: java.lang.Exception) {
            // The receiver was not registered.
            // There is nothing to do in that case.
            // Everything is fine.
        }
        try {
            context.unregisterReceiver(intentTriggerUpReciver)
        } catch (exception: java.lang.Exception) {
            // The receiver was not registered.
            // There is nothing to do in that case.
            // Everything is fine.
        }
    }

    override fun onPauseAfter() {
        if (bcr != null) {
            state.update { DecodeState.STATE_IDLE }
            bcr!!.release()
            bcr = null
        }
    }

    override fun onResume(
        onFinish: () -> Unit,
        cb: BarCodeReader.DecodeCallback,
        er: BarCodeReader.ErrorCallback,
        st: SurfaceTexture.OnFrameAvailableListener
    ) {
        val configuration = ConfigurationIni()
        configuration.readFile()

        if (configuration.ReadBoolean("Device_Scanwedge", false)) {
            onFinish()
        } else {
            System.loadLibrary("IAL.hht")
            System.loadLibrary("SDL.hht")
            System.loadLibrary("barcodereader90.hht") // Android 9.0
            state .update { DecodeState.STATE_IDLE }

            initBarCodeReader(cb, er, st)
        }
    }
}