package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.data.barcode.Barcode
import com.flyview.core.data.barcode.InvBarcodeBinder
import com.flyview.core.domain.barcode.BarcodeBinder
import com.flyview.core.domain.barcode.BarcodeReader
import com.flyview.core.domain.barcode.BarcodeReaderData
import com.flyview.core.utils.GS1
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.Product
import com.flyview.inventory_feature.ui.details.toolbar.RealDocumentDetailsToolbarComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealDocumentDetailsComponent(
    componentContext: ComponentContext,
    private val document: Document,
    private val onBack: () -> Unit,
    private val repository: InventoryRepository,
    private val barcodeReader: BarcodeReader
) : ComponentContext by componentContext, DocumentDetailsComponent {

    private val coroutineScope = componentCoroutineScope()

    private val barcodeBinder: BarcodeBinder = InvBarcodeBinder()

    override val productsPager = this.repository.getProductsPager(this.document.id)

    override val toolbarComponent = RealDocumentDetailsToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        barcodeReader = this.barcodeReader
    )

    private fun onReadBarcode(code: String) = coroutineScope.launch {

        val barcodeType = barcodeBinder.CreateBarcode(data = code)

        if (barcodeType is Barcode.Unknown) TODO("Некорректный код")

        /*
        when (barcodeType) {
            Barcode.DataMatrix85 -> TODO()
            Barcode.EAN13 -> TODO()
        }*/
    }

    override fun onItemClick(product: Product) {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(coroutineScope)
    }
}