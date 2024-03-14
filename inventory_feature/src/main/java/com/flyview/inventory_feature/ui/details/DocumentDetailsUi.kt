package com.flyview.inventory_feature.ui.details

import android.app.Activity
import android.view.MotionEvent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.flyview.core.R
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE
import com.flyview.inventory_feature.ui.details.item.ProductItem
import com.flyview.inventory_feature.ui.details.toolbar.DocumentDetailsToolbarUi
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun DocumentDetailsUi(component: DocumentDetailsComponent) {

    val productsData = component.productsPager.collectAsLazyPagingItems()
    val showCamera = component.toolbarComponent.camera.collectAsState()
    val handleScanCode = component.idleHandleScanCode.collectAsState()

    val context = LocalContext.current

    val compoundBarcodeView = remember {
        CompoundBarcodeView(context).apply {
            val capture = CaptureManager(context as Activity, this)
            capture.initializeFromIntent(context.intent, null)
            this.setStatusText("")
            capture.decode()
            this.decodeContinuous { result ->
                if (handleScanCode.value) {
                    return@decodeContinuous
                }
                //component.setScanFlag(true)
                result.text?.let { barCodeOrQr ->
                    component.onHandleReadBarcode(barCodeOrQr)
                    //Do something and when you finish this something
                    //put scanFlag = false to scan another item
                    //component.setScanFlag(false)
                }
                //If you don't put this scanFlag = false, it will never work again.
                //you can put a delay over 2 seconds and then scanFlag = false to prevent multiple scanning

            }
        }
    }

    Scaffold(
        topBar = { DocumentDetailsToolbarUi(component.toolbarComponent) })
    { paddingValues ->
        Column(
            modifier = Modifier/*
                .fillMaxSize()*/
                .padding(paddingValues)/*
                .height(200.dp)*/
        ) {
            if (showCamera.value) {
                AndroidView(
                    modifier = Modifier
                        .height(200.dp),
                    factory = { compoundBarcodeView },
                )

                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> {
                                    component.setHandleState(false)
                                }

                                MotionEvent.ACTION_UP -> {
                                    component.setHandleState(true)
                                }

                                else -> false
                            }
                            true
                        }
                        .align(Alignment.End)
                        .padding(4.dp),
                    onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_qr_code_scan
                            ),
                            contentDescription = "scan_qr_code",
                            modifier = Modifier.size(ICON_SIZE)
                        )
                    },
                    text = { Text(text = "Сканировать") }
                )

                DisposableEffect(key1 = "someKey") {
                    compoundBarcodeView.resume()
                    onDispose {
                        compoundBarcodeView.pause()
                    }
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Row {
                    Row {
                        Text(text = "Записей:", modifier = Modifier.padding(end = 4.dp))
                        Text(text = productsData.itemCount.toString())
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(productsData) { product ->
                    product?.let {
                        ProductItem(
                            model = it,
                            onClick = component::onItemClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DocumentDetailsUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentDetailsUi(FakeDocumentDetailsComponent())
    }
}

@Preview(name = "light")
@Composable
fun DocumentDetailsUiPreviewLight() {
    DocumentDetailsUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentDetailsUiPreviewDark() {
    DocumentDetailsUiPreview(darkTheme = true)
}