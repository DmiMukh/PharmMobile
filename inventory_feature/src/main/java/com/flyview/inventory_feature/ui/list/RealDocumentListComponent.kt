package com.flyview.inventory_feature.ui.list

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.ui.list.toolbar.RealDocumentListToolbarComponent
import kotlinx.coroutines.launch

class RealDocumentListComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onDocumentDetails: (Document) -> Unit,
    private val repository: InventoryRepository
) : ComponentContext by componentContext, DocumentListComponent {

    private val coroutineScope = componentCoroutineScope()

    override val documentsPager = repository.getDocumentsPager()

    override val toolbarComponent = RealDocumentListToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack
    )

    override fun onBackClick() = this.onBack.invoke()

    override fun onCreateDocumentClick() {
        coroutineScope.launch {
            val document = repository.createDocument()
            onDocumentDetails.invoke(document)
        }
    }

    override fun onItemClick(document: Document) = this.onDocumentDetails.invoke(document)
}