package com.flyview.documents_feature.ui.placement

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.BarcodeReaderData
import com.flyview.core.barcode.data.code.EAN13
import com.flyview.core.barcode.data.code.UnknownBarcode
import com.flyview.core.barcode.domain.BarcodeBinder
import com.flyview.core.media.AppSound
import com.flyview.core.media.AudioPlayer
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentScope
import com.flyview.documents_feature.data.PlacementBarcodeBinder
import com.flyview.documents_feature.domain.PlacementRepository
import com.flyview.documents_feature.domain.model.Cell
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.placement.navbar.RealPlacementNavbarComponent
import com.flyview.documents_feature.ui.placement.toolbar.RealPlacementToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealPlacementComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val audioPlayer: AudioPlayer,
    private val messageService: MessageService,
    private val repository: PlacementRepository
) : ComponentContext by componentContext, PlacementComponent {

    private val barcodeBinder: BarcodeBinder = PlacementBarcodeBinder()

    override val cell = MutableStateFlow(Cell(-1, ""))

    override val navbarComponent = RealPlacementNavbarComponent(
        componentContext = componentContext
    )

    override val toolbarComponent = RealPlacementToolbarComponent(
        componentContext = componentContext,
        currentDocument = Document(),
        onBack = this.onBack,
        onSave = { }
    )

    private fun onReadBarcode(code: String) = componentScope.launch {

        val barcode = barcodeBinder.createBarcode(data = code)

        if (isInvalidCode(barcode)) return@launch

        // "Обработка кода"

        if (barcode is EAN13 && code.substring(1, 4) == "9999") {

            val cell = repository.getCell(code)

            if (cell == null) {
                messageService.showMessage(
                    Message(text = "Ячейка не найдена!")
                )
                return@launch
            }

        } else {
            val product = repository.getProduct(
                cell = cell.value,
                barcode = barcode
            )

            if (product == null) {
                messageService.showMessage(
                    Message(text = "Нет товара по коду ${code}!")
                )
                return@launch
            }

            TODO("Отображение ввода кол-ва")
            TODO("Изменение кол-ва")

            repository.collectProduct(
                cell = cell.value,
                product = product,
                quantity = 0.0
            )
        }
    }

    init {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(componentScope)
    }

    private fun isInvalidCode(barcode: Barcode): Boolean {
        if (barcode is UnknownBarcode) {
            messageService.showMessage(Message(text = "Некорректный код!"))
            audioPlayer.play(AppSound.ERROR)
            return true
        }
        return false
    }
}