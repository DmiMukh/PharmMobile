package com.flyview.pharmmobile.features.inventory.ui.list.toolbar

import com.arkivanov.decompose.ComponentContext

class RealInventoryDocumentListToolbarComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit
) : ComponentContext by componentContext, InventoryDocumentListToolbarComponent {
    override fun onBackClick() = this.onBackClick.invoke()
}