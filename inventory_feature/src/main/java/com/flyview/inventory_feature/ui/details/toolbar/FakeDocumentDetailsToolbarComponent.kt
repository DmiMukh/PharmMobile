package com.flyview.inventory_feature.ui.details.toolbar

import kotlinx.coroutines.flow.MutableStateFlow

class FakeDocumentDetailsToolbarComponent: DocumentDetailsToolbarComponent {

    override val barcodeReaderConnected = MutableStateFlow(false)
    override fun onBackClick() = Unit
    override fun onUsbDeviceConnectionClick() = Unit
}