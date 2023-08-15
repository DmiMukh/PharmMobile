package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.inventory_feature.data.InvBarcodeBinder
import com.flyview.core.barcode.data.code.EAN13
import com.flyview.core.barcode.data.code.UnknownBarcode
import com.flyview.core.barcode.domain.BarcodeBinder
import com.flyview.core.barcode.domain.BarcodeReader
import com.flyview.core.barcode.data.BarcodeReaderData
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
            TODO("Добавить звук!")
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
            TODO("Добавить звук!")
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