package com.flyview.documents_feature.ui.scan.mark_list

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.documents_feature.domain.model.MarkCode
import com.flyview.documents_feature.ui.scan.mark_list.navbar.RealMarkListNavbarComponent
import com.flyview.documents_feature.ui.scan.mark_list.toolbar.RealMarkListToolbarComponent

class RealMarkListComponent(
    componentContext: ComponentContext,
    private val messageService: MessageService
) : ComponentContext by componentContext, MarkListComponent {
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
        TODO("Проверяем, сохранена ли запись")
        if (true) return

        messageService.showMessage(
            Message(
                "код?",
                actionTitle = "Удалить",
                action = { TODO("удаление записи") }
            )
        )
    }
}