package com.flyview.core.barcode.data

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import android.os.Build
import com.flyview.core.android.AndroidIntent
import com.flyview.core.barcode.domain.BarcodeReader
import com.flyview.core.media.AppSound
import com.flyview.core.media.AudioPlayer
import com.flyview.core.utils.APPLICATION_ID
import com.hoho.android.usbserial.driver.CdcAcmSerialDriver
import com.hoho.android.usbserial.driver.UsbSerialDriver
import com.hoho.android.usbserial.driver.UsbSerialPort
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class UsbBarcodeReader(
    private val context: Context,
    private val usbManager: UsbManager,
    private val audioPlayer: AudioPlayer
) : BarcodeReader, AndroidIntent {

    private val coroutineScope = CoroutineScope(EmptyCoroutineContext + Dispatchers.IO)

    override val intentFilter = IntentFilter(Companion.INTENT_ACTION_GRANT_USB)

    private val usbPermissionIntent = PendingIntent.getBroadcast(
        this.context,
        0,
        Intent(Companion.INTENT_ACTION_GRANT_USB),
        PendingIntent.FLAG_MUTABLE
    )


    override val connected = MutableStateFlow(false)

    private val connection = MutableStateFlow<UsbDeviceConnection?>(null)
    private val port = MutableStateFlow<UsbSerialPort?>(null)
    private val device = MutableStateFlow<UsbDevice?>(null)

    override fun onConnect() {
        val deviceList = usbManager.deviceList.toList()
        if (deviceList.isEmpty()) return

        device.value = deviceList.first().second

        if (isPermissionGranted(device.value))
            connectDevice(device.value)
    }

    private fun isPermissionGranted(device: UsbDevice?): Boolean {
        if (!usbManager.hasPermission(device)) {
            usbManager.requestPermission(
                device,
                usbPermissionIntent
            )
            return false
        }
        return true
    }

    private fun connectDevice(device: UsbDevice?) {
        connection.value = usbManager.openDevice(device)

        val driver: UsbSerialDriver = CdcAcmSerialDriver(device)
        if (driver.ports.isEmpty()) return

        port.value = driver.ports.first()

        connected.value = (connection.value != null && port.value != null)
    }

    override fun onDisconnect() {
        port.value?.let {
            if (it.isOpen) it.close()
        }

        connection.value?.let {
            it.close()
            connection.value = null
        }

        connected.update { false }
    }

    override fun onBroadcastReceive(context: Context?, intent: Intent?) {
        if (Companion.INTENT_ACTION_GRANT_USB == intent?.action) {
            val isPermissionGranted = intent.extras?.getBoolean(
                UsbManager.EXTRA_PERMISSION_GRANTED, false
            ) ?: false

            if (isPermissionGranted) {
                @Suppress("DEPRECATION") val usbDevice: UsbDevice? =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(UsbManager.EXTRA_DEVICE, UsbDevice::class.java)
                    } else {
                        intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                    }

                connectDevice(usbDevice)
            }
        }

        if (UsbManager.ACTION_USB_DEVICE_ATTACHED == intent?.action) {
            @Suppress("DEPRECATION") val usbDevice: UsbDevice? =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(UsbManager.EXTRA_DEVICE, UsbDevice::class.java)
                } else {
                    intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                }

            device.update { usbDevice }

            if (!usbManager.hasPermission(usbDevice)) {
                usbManager.requestPermission(
                    usbDevice,
                    usbPermissionIntent
                )
            }
        }

        /*
        if (UsbManager.ACTION_USB_DEVICE_DETACHED == intent?.action) {
            // Device removed
            synchronized (this) {
                // ... Check to see if usbDevice is yours and cleanup ...
            }
        }
        */
    }

    override fun switchConnection() {
        if (connected.value) this.onDisconnect()
        else this.onConnect()
    }

    private fun read() {
        if (port.value == null) return

        val responseData = ByteArray(Companion.MAX_BUFFER_SIZE)

        try {
            port.value?.read(responseData, Companion.READ_WAIT_MILLIS.toInt())
        } catch (ex: Exception) {
            onDisconnect()
            return
            // TODO("Надо проверить ошибку с другими устройствами.")
        }

        val resultData = String(responseData)
            .replace("\u0000", "")
            .trim()

        BarcodeReaderData.data.update { resultData }
    }

    init {
        this.intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)

        port.onEach {
            it?.let {
                it.open(connection.value)
                it.setParameters(
                    Companion.BAUD_RATE, Companion.DATA_BITS,
                    Companion.STOP_BITS, UsbSerialPort.PARITY_NONE
                )
            }
        }.launchIn(this.coroutineScope)

        coroutineScope.launch {
            while (true) {
                if (connected.value) read()

                delay(Companion.READ_WAIT_MILLIS)
            }
        }

        connected.onEach {
            if (it) audioPlayer.play(AppSound.USB_ON)
            else audioPlayer.play(AppSound.USB_OFF)
        }.launchIn(this.coroutineScope)
    }

    companion object {
        private const val READ_WAIT_MILLIS = 100L

        private const val MAX_BUFFER_SIZE = 1024

        private const val BAUD_RATE = 115200

        private const val DATA_BITS = 8

        private const val STOP_BITS = 1

        private const val INTENT_ACTION_GRANT_USB = APPLICATION_ID.plus(".GRANT_USB")
    }
}