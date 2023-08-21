package com.flyview.inventory_feature.ui.details.toolbar

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.domain.BarcodeReader

class RealDocumentDetailsToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val barcodeReader: BarcodeReader,
    private val onTestBarcodeClick: () -> Unit
) : ComponentContext by componentContext, DocumentDetailsToolbarComponent {

    override val barcodeReaderConnected = barcodeReader.connected
    override fun onBackClick() = this.onBack.invoke()
    override fun onUsbDeviceConnectionClick() = this.barcodeReader.switchConnection()
    override fun onTestBarcodeClick() = this.onTestBarcodeClick.invoke()
}