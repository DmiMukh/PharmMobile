package com.flyview.inventory_feature.ui.list

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentScope
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.ui.list.dialog.ListDialogState
import com.flyview.inventory_feature.ui.list.dialog.RealListDialogComponent
import com.flyview.inventory_feature.ui.list.toolbar.RealDocumentListToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealDocumentListComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onDocumentDetails: (Document) -> Unit,
    private val repository: InventoryRepository,
    private val messageService: MessageService
) : ComponentContext by componentContext, DocumentListComponent {

    private val createDocumentEnabled = MutableStateFlow(true)
    override val dialogComponent = RealListDialogComponent(
        componentContext = componentContext,
        repository = repository
    )


    override val documentsPager = repository.getDocumentsPager()

    override val toolbarComponent = RealDocumentListToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onSendClick = { onSendClick() }
    )

    private fun onSendClick() {
        messageService.showMessage(
            Message(
                text = "Отправить документы?",
                actionTitle = "Отправить",
                action = {
                    dialogComponent.state.value = ListDialogState.SendDocuments
                }
            )
        )
    }

    override fun onBackClick() = this.onBack.invoke()

    override fun onCreateDocumentClick() {
        if (createDocumentEnabled.value) {
            createDocumentEnabled.value = false
            componentScope.launch {
                val document = repository.createDocument()
                onDocumentDetails.invoke(document)
                createDocumentEnabled.value = true
                return@launch
            }
        }
    }

    override fun onItemClick(document: Document) = this.onDocumentDetails.invoke(document)
}