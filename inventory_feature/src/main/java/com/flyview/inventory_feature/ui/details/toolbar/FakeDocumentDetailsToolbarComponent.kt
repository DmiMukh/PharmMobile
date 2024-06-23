package com.flyview.inventory_feature.ui.details.toolbar

import com.flyview.inventory_feature.domain.model.Document
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeDocumentDetailsToolbarComponent: DocumentDetailsToolbarComponent {

    override val barcodeReaderConnected = MutableStateFlow(false)
    override val camera = MutableStateFlow(false)
    override val flashlight = MutableStateFlow(false)
    override val model = Document()
    override fun onBackClick() = Unit
    override fun onCameraClick() = Unit
    override fun onFlashlightClick() = Unit

    override fun onTestClick() = Unit

    override fun onUsbDeviceConnectionClick() = Unit
}