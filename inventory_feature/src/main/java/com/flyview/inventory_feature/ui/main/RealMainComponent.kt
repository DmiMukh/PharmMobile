package com.flyview.inventory_feature.ui.main

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentScope
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.ui.main.dialog.MainDialogState
import com.flyview.inventory_feature.ui.main.dialog.RealMainDialogComponent
import com.flyview.inventory_feature.ui.main.toolbar.RealMainToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealMainComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onDocumentsClick: () -> Unit,
    private val repository: InventoryRepository,
    private val messageService: MessageService
) : ComponentContext by componentContext, MainComponent {
    override val formattedDateOfData = MutableStateFlow("")

    override val dialogComponent = RealMainDialogComponent(
        componentContext = componentContext,
        onDismiss = {},
        repository = this.repository
    )

    override val toolbarComponent = RealMainToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack
    )

    override fun onClearDataClick() {
        messageService.showMessage(
            Message(
                text = "Очистить данные?",
                actionTitle = "Подтвердить",
                action = {
                    componentScope.launch {
                        repository.clearData()
                        return@launch
                    }
                }
            )
        )
    }

    override fun onDocumentsClick() = this.onDocumentsClick.invoke()
    override fun onUploadDataClick() {
        this.dialogComponent.state.value = MainDialogState.UploadData
    }
}