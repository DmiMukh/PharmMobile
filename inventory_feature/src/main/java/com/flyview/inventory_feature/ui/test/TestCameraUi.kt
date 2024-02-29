package com.flyview.inventory_feature.ui.test

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestCameraUi(component: TestCameraComponent) {

    val scanData = component.scanData.collectAsState()
    val scanFlag = component.scanFlag.collectAsState()

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = {
            result -> Log.i(TAG, "scanned code: ${result.contents}")
            component.setScanData(result.contents)
        }
    )

    val context = LocalContext.current

    val compoundBarcodeView = remember {
        CompoundBarcodeView(context).apply {
            val capture = CaptureManager(context as Activity, this)
            capture.initializeFromIntent(context.intent, null)
            this.setStatusText("")
            capture.decode()
            this.decodeContinuous { result ->
                if(scanFlag.value){
                    return@decodeContinuous
                }
                component.setScanFlag(true)
                result.text?.let { barCodeOrQr->
                    component.setScanData(barCodeOrQr)
                    //Do something and when you finish this something
                    //put scanFlag = false to scan another item
                    //component.setScanFlag(false)
                }
                //If you don't put this scanFlag = false, it will never work again.
                //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning

            }
        }
    }
    /*
    val barcodeView = remember {
        BarcodeView(context).apply {
            val captureManager = CaptureManager(context as Activity, this)

        }
    }
    */


    Scaffold {
        paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            AndroidView(
                modifier = Modifier,
                factory = { compoundBarcodeView },
            )

            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = { component.setScanFlag(false) }
            ) {
                Text(text = "Scan")
            }
        }
    }

    /*
    Column {
        Row {
            /*
            Button(onClick = { scanLauncher.launch(ScanOptions()) }) {
                Text(text = "Scan barcode DLG")
            }*/

            Button(onClick = { component.setScanFlag(false) }) {
                Text(text = "Scan barcode 2")
            }
        }

        Text(text = scanData.value)

        AndroidView(
            modifier = Modifier,
            factory = { compoundBarcodeView },
        )
    }
    */

    DisposableEffect(key1 = "someKey" ){
        compoundBarcodeView.resume()
        onDispose {
            compoundBarcodeView.pause()
        }
    }
}