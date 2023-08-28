package com.flyview.inventory_feature.ui.details.toolbar

import com.flyview.inventory_feature.domain.model.Document
import kotlinx.coroutines.flow.StateFlow

interface DocumentDetailsToolbarComponent {

    val barcodeReaderConnected: StateFlow<Boolean>

    val model: Document
    fun onBackClick()
    fun onTestClick()
    fun onUsbDeviceConnectionClick()
}