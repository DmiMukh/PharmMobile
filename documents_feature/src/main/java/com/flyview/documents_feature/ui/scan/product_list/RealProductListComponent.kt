package com.flyview.documents_feature.ui.scan.product_list

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.data.BarcodeReaderData
import com.flyview.core.barcode.data.code.UnknownBarcode
import com.flyview.core.barcode.domain.BarcodeBinder
import com.flyview.core.media.AppSound
import com.flyview.core.media.AudioPlayer
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentScope
import com.flyview.documents_feature.data.ProductBarcodeBinder
import com.flyview.documents_feature.domain.DocumentsRepository
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.ui.scan.product_list.toolbar.RealProductListToolbarComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealProductListComponent(
    componentContext: ComponentContext,
    private val currentDocument: Document,
    private val onBack: () -> Unit,
    private val audioPlayer: AudioPlayer,
    private val messageService: MessageService,
    private val repository: DocumentsRepository
) : ComponentContext by componentContext, ProductListComponent {

    private val barcodeBinder: BarcodeBinder = ProductBarcodeBinder()

    override val productsPager = repository.getProductsPager(this.currentDocument.id)
    override val toolbarComponent = RealProductListToolbarComponent(
        componentContext = componentContext,
        onBack = onBack,
        onSave = {}
    )

    override fun onItemClick(model: Product) {
        if (model.certificate.marked) {
            TODO("Маркировка: открытие списка кодов")
            return
        }
        TODO("Обычный: открытие изменение кол-ва")
    }

    init {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(componentScope)
    }

    private fun onReadBarcode(code: String) = componentScope.launch {
        val barcode = barcodeBinder.createBarcode(data = code)

        // Проверка корректности кода
        if (barcode is UnknownBarcode) {
            messageService.showMessage(Message(text = "Некорректный код!"))
            audioPlayer.play(AppSound.ERROR)
            return@launch
        }

        val ean13 = barcode.extractor.getEAN()

        // обрабатываем обычный ШК
        // Обрабатываем маркировку
    }
}