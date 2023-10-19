package com.flyview.documents_feature.ui.scan.mark_list

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.BarcodeReaderData
import com.flyview.core.barcode.data.code.Code128
import com.flyview.core.barcode.data.code.DataMatrix85
import com.flyview.core.barcode.data.code.UnknownBarcode
import com.flyview.core.barcode.domain.BarcodeBinder
import com.flyview.core.media.AppSound
import com.flyview.core.media.AudioPlayer
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentScope
import com.flyview.documents_feature.data.MarkBarcodeBinder
import com.flyview.documents_feature.domain.model.MarkCode
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.domain.repository.MarkRepository
import com.flyview.documents_feature.ui.scan.mark_list.navbar.MarkListTab
import com.flyview.documents_feature.ui.scan.mark_list.navbar.RealMarkListNavbarComponent
import com.flyview.documents_feature.ui.scan.mark_list.toolbar.RealMarkListToolbarComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealMarkListComponent(
    componentContext: ComponentContext,
    private val currentProduct: Product,
    private val onBack: () -> Unit,
    private val audioPlayer: AudioPlayer,
    private val messageService: MessageService,
    private val repository: MarkRepository
) : ComponentContext by componentContext, MarkListComponent {

    private val barcodeBinder: BarcodeBinder = MarkBarcodeBinder()

    override val navbarComponent = RealMarkListNavbarComponent(
        componentContext = componentContext,
        onChange = { TODO("Добавить обновление списка при переключении") }
    )
    override val toolbarComponent = RealMarkListToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onSave = {
            componentScope.launch {
                repository.saveProductCodes(product = currentProduct)
            }
        }
    )

    override fun onItemClick(code: MarkCode) {
        if (code.saved) return

        messageService.showMessage(
            Message(
                text = code.code,
                actionTitle = "Удалить",
                action = {
                    componentScope.launch {
                        repository.deleteCode(code = code.code)
                    }
                }
            )
        )
    }

    init {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(componentScope)
    }

    private fun onReadBarcode(code: String) = componentScope.launch {
        val barcode = barcodeBinder.createBarcode(data = code)

        setTab(barcode)
        if (!isValidCode(barcode)) return@launch

        val shortCode = barcode.extractor.getShortCode()

        if (isBinded(shortCode)) return@launch

        repository.putProductCode(barcode)
    }

    private fun setTab(barcode: Barcode) {
        when (barcode) {
            is Code128 -> navbarComponent.onClick(MarkListTab.BOX)
            is DataMatrix85 -> navbarComponent.onClick(MarkListTab.PACK)
        }
    }

    private fun isValidCode(barcode: Barcode): Boolean {
        if (barcode is UnknownBarcode) {
            messageService.showMessage(Message(text = "Некорректный код!"))
            audioPlayer.play(AppSound.ERROR)
            return false
        }
        return true
    }

    private suspend fun isBinded(shortCode: String): Boolean {

        val product = repository.getProductByCode(shortCode)

        product?.let {
            messageService.showMessage(
                Message(
                    text = "Код уже связан!",
                    actionTitle = "Детали",
                    action = { TODO("Отобразить информацию о товаре") }
                )
            )
            audioPlayer.play(AppSound.ERROR)
            return true
        }

        return false
    }
}