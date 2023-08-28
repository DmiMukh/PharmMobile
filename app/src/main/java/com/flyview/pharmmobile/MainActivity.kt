package com.flyview.pharmmobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.SurfaceTexture
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.core.android.AndroidIntent
import com.flyview.core.barcode.domain.BarcodeReader
import com.flyview.core.koin
import com.flyview.core.theme.AppTheme
import com.flyview.pharmmobile.root.createRootComponent
import com.flyview.pharmmobile.root.ui.RootContent
import com.zebra.adc.decoder.BarCodeReader

class MainActivity : ComponentActivity(), BarCodeReader.DecodeCallback, BarCodeReader.ErrorCallback,
    SurfaceTexture.OnFrameAvailableListener  {

    private var barcodeReaderReciever: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentFactory = application.koin.get<ComponentFactory>()
        val rootComponent = componentFactory.createRootComponent(defaultComponentContext())

        /* Сканер, работающий через VCOM */
        val barcodeReader = (application.koin.get<BarcodeReader>() as AndroidIntent)
        // Регистрируем событие
        barcodeReaderReciever = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                barcodeReader.onBroadcastReceive(context, intent)
            }
        }
        registerReceiver(barcodeReaderReciever, barcodeReader.intentFilter)

        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootContent(
                        component = rootComponent
                    )
                }
            }
        }
    }

    override fun onDestroy() {

        /* Сканер, работающий через VCOM */
        if (barcodeReaderReciever != null) {
            unregisterReceiver(barcodeReaderReciever)
            barcodeReaderReciever = null
        }

        super.onDestroy()
    }

    override fun onDecodeComplete(
        symbology: Int,
        length: Int,
        data: ByteArray?,
        reader: BarCodeReader?
    ) {
        TODO("Not yet implemented")
    }

    override fun onEvent(event: Int, info: Int, data: ByteArray?, reader: BarCodeReader?) {
        TODO("Not yet implemented")
    }

    override fun onError(error: Int, reader: BarCodeReader?) {
        TODO("Not yet implemented")
    }

    override fun onFrameAvailable(surfaceTexture: SurfaceTexture?) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        /*
        TODO("")
        if (abrcodeReaderT200.connected.value) {

        }
        */
    }
}