package com.flyview.documents_feature.ui.scan.mark_list

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.data.BarcodeReaderData
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
import com.flyview.documents_feature.ui.scan.mark_list.navbar.MarkListTab
import com.flyview.documents_feature.ui.scan.mark_list.navbar.RealMarkListNavbarComponent
import com.flyview.documents_feature.ui.scan.mark_list.toolbar.RealMarkListToolbarComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.random.Random

class RealMarkListComponent(
    componentContext: ComponentContext,
    private val audioPlayer: AudioPlayer,
    private val messageService: MessageService
) : ComponentContext by componentContext, MarkListComponent {

    private val barcodeBinder: BarcodeBinder = MarkBarcodeBinder()

    override val navbarComponent = RealMarkListNavbarComponent(
        componentContext = componentContext,
        onChange = { TODO("Добавить обновление списка при переключении") }
    )
    override val toolbarComponent = RealMarkListToolbarComponent(
        componentContext = componentContext,
        onBack = {},
        onSave = {}
    )

    override fun onItemClick(code: MarkCode) {
        if (code.saved) return

        messageService.showMessage(
            Message(
                "код?",
                actionTitle = "Удалить",
                action = { TODO("удаление записи") }
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

        // Проверка корректности кода
        if (barcode is UnknownBarcode) {
            messageService.showMessage(Message(text = "Некорректный код!"))
            audioPlayer.play(AppSound.ERROR)
            return@launch
        }

        val shortCode = barcode.extractor.getShortCode()

        // Переключаемся на нужную вкладку
        if (barcode is DataMatrix85) navbarComponent.onClick(MarkListTab.PACK)
        else navbarComponent.onClick(MarkListTab.BOX)

        // Проверяем код
        val isExists = Random(1).nextBoolean()

        if (isExists) {
            messageService.showMessage(
                Message(
                    text = "Код уже связан!",
                    actionTitle = "Детали",
                    action = { TODO("Отобразить информацию о товаре") }
                )
            )
            audioPlayer.play(AppSound.ERROR)
            return@launch
        }
    }
}