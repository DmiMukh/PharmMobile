package com.flyview.inventory_feature.ui.details.toolbar

import kotlinx.coroutines.flow.StateFlow

interface DocumentDetailsToolbarComponent {

    val barcodeReaderConnected: StateFlow<Boolean>
    fun onBackClick()
    fun onUsbDeviceConnectionClick()
    fun onTestBarcodeClick()
}