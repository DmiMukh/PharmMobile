package com.flyview.inventory_feature.ui.list

import com.arkivanov.decompose.ComponentContext
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.ui.list.toolbar.RealDocumentListToolbarComponent

class RealDocumentListComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onDocumentClick: () -> Unit,
    private val inventoryRepository: InventoryRepository
) : ComponentContext by componentContext, DocumentListComponent {

    override val documentsPager = inventoryRepository.documentsPager()

    override val toolbarComponent = RealDocumentListToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack
    )

    override fun onBackClick() = this.onBack.invoke()

    override fun onCreateDocumentClick() {
        TODO("Добавить создание документа")
        this.onDocumentClick.invoke()
    }

    override fun onItemClick(document: Document) = this.onDocumentClick.invoke()
}