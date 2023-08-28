package com.flyview.inventory_feature.ui.details.toolbar

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.domain.BarcodeReader
import com.flyview.inventory_feature.domain.model.Document

class RealDocumentDetailsToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onReadBarcode: () -> Unit,
    private val document: Document,
    private val barcodeReader: BarcodeReader
) : ComponentContext by componentContext, DocumentDetailsToolbarComponent {

    override val barcodeReaderConnected = barcodeReader.connected
    override val model: Document
        get() = this.document

    override fun onBackClick() = this.onBack.invoke()
    override fun onTestClick() = this.onReadBarcode.invoke()

    override fun onUsbDeviceConnectionClick() = this.barcodeReader.switchConnection()
}