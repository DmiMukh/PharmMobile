package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.data.BarcodeReaderData
import com.flyview.core.barcode.data.code.EAN13
import com.flyview.core.barcode.data.code.UnknownBarcode
import com.flyview.core.barcode.domain.BarcodeBinder
import com.flyview.core.barcode.domain.BarcodeReader
import com.flyview.core.media.AppSound
import com.flyview.core.media.AudioPlayer
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentScope
import com.flyview.inventory_feature.data.InvBarcodeBinder
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.model.Product
import com.flyview.inventory_feature.domain.model.isValid
import com.flyview.inventory_feature.ui.details.toolbar.RealDocumentDetailsToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RealDocumentDetailsComponent(
    componentContext: ComponentContext,
    private val document: Document,
    private val onBack: () -> Unit,
    private val onEditProduct: (Product) -> Unit,
    private val repository: InventoryRepository,
    private val barcodeReader: BarcodeReader,
    private val messageService: MessageService,
    private val audioPlayer: AudioPlayer
) : ComponentContext by componentContext, DocumentDetailsComponent {

    private val barcodeBinder: BarcodeBinder = InvBarcodeBinder()
    override val idleHandleScanCode = MutableStateFlow(true)

    override val productsPager = this.repository.getProductsPager(this.document.id)

    override val toolbarComponent = RealDocumentDetailsToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onReadBarcode = { onReadBarcode("978020137962") },
        document = this.document,
        barcodeReader = this.barcodeReader
    )

    private fun onReadBarcode(code: String) = componentScope.launch {

        if (document.sended) {
            messageService.showMessage(Message(text = "Нельзя менять отправленный документ!"))
            audioPlayer.play(AppSound.ERROR)
            return@launch
        }

        val barcode = barcodeBinder.createBarcode(data = code)

        // Проверка корректности кода
        if (barcode is UnknownBarcode) {
            messageService.showMessage(Message(text = "Некорректный код!"))
            audioPlayer.play(AppSound.ERROR)
            return@launch
        }

        val shortCode = barcode.extractor.getShortCode()
        val marked = barcode !is EAN13

        val product: Product = repository.getProduct(
            code = shortCode,
            documentId = document.id,
            marked = marked
        )

        if (!product.isValid()) {
            messageService.showMessage(Message(text = "Не найден товар!"))
            audioPlayer.play(AppSound.ERROR)
            return@launch
        }

        if (product.sgtin.isNotEmpty() && product.quantity > 0) {
            messageService.showMessage(Message(text = "SGTIN уже добавлен!"))
            audioPlayer.play(AppSound.ERROR)
            return@launch
        }

        repository.upsertProduct(
            product = product,
            documentId = document.id,
            newQuantity = product.quantity.plus(1)
        )
        return@launch
    }

    override fun onItemClick(product: Product) {

        if (document.sended) {
            componentScope.launch {
                messageService.showMessage(Message(text = "Нельзя менять отправленный документ!"))
                audioPlayer.play(AppSound.ERROR)
                return@launch
            }

            return
        }

        onEditProduct.invoke(product)
    }

    override fun onHandleReadBarcode(code: String) {
        audioPlayer.play(AppSound.BEEP)

        this.idleHandleScanCode.update { true }

        this.onReadBarcode(code)
    }

    override fun setHandleState(idle: Boolean) {
        this.idleHandleScanCode.update { idle }
    }

    init {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(componentScope)
    }
}