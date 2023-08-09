package com.flyview.pharmmobile.features.inventory.ui.list

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.features.inventory.ui.list.toolbar.RealInventoryDocumentListToolbarComponent

class RealInventoryDocumentListComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit
) : ComponentContext by componentContext, InventoryDocumentListComponent {

    override val toolbarComponent = RealInventoryDocumentListToolbarComponent(
        componentContext = componentContext,
        onBackClick = this.onBackClick
    )
}