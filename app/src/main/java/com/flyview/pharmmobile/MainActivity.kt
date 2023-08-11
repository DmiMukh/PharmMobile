package com.flyview.pharmmobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.core.domain.barcode.BarcodeReader
import com.flyview.core.koin
import com.flyview.core.theme.AppTheme
import com.flyview.pharmmobile.root.ui.RootContent
import com.flyview.pharmmobile.root.createRootComponent

class MainActivity : ComponentActivity() {

    private var barcodeReaderReciever: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentFactory = application.koin.get<ComponentFactory>()
        val rootComponent = componentFactory.createRootComponent(defaultComponentContext())

        val barcodeReader: BarcodeReader = application.koin.get()
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
        if (barcodeReaderReciever != null) {
            unregisterReceiver(barcodeReaderReciever)
            barcodeReaderReciever = null
        }

        super.onDestroy()
    }
}