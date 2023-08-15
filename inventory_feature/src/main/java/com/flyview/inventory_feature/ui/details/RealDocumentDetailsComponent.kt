package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.data.barcode.InvBarcodeBinder
import com.flyview.core.data.barcode.code.EAN13
import com.flyview.core.data.barcode.code.UnknownBarcode
import com.flyview.core.domain.barcode.BarcodeBinder
import com.flyview.core.domain.barcode.BarcodeReader
import com.flyview.core.domain.barcode.BarcodeReaderData
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.Product
import com.flyview.inventory_feature.domain.isValid
import com.flyview.inventory_feature.ui.details.toolbar.RealDocumentDetailsToolbarComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealDocumentDetailsComponent(
    componentContext: ComponentContext,
    private val document: Document,
    private val onBack: () -> Unit,
    private val repository: InventoryRepository,
    private val barcodeReader: BarcodeReader,
    private val messageService: MessageService
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

        val barcode = barcodeBinder.createBarcode(data = code)

        if (barcode is UnknownBarcode) {
            messageService.showMessage(Message(text = "Некорректный код!"))
            return@launch
            TODO("Некорректный код!")
        }

        val shortCode = barcode.getShortCode()
        val marked = !(barcode is EAN13)

        val product: Product = repository.getProduct(
            code = shortCode,
            documentId = document.id,
            marked = marked
        )

        if (!product.isValid()) {
            messageService.showMessage(Message(text = "Не найден товар!"))
            return@launch
            TODO("Товар не найден!")
        }

        repository.upsertProduct(
            product = product,
            documentId = document.id
        )
    }

    override fun onItemClick(product: Product) {
        TODO("Изменение кол-ва")
    }

    init {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(coroutineScope)
    }
}