package com.flyview.inventory_feature.ui.details.toolbar

import com.flyview.inventory_feature.domain.model.Document
import kotlinx.coroutines.flow.MutableStateFlow

class FakeDocumentDetailsToolbarComponent: DocumentDetailsToolbarComponent {

    override val barcodeReaderConnected = MutableStateFlow(false)
    override val model = Document()
    override fun onBackClick() = Unit
    override fun onTestClick() = Unit

    override fun onUsbDeviceConnectionClick() = Unit
}