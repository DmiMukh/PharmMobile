package com.flyview.pharmmobile.features.inventory.ui.main.toolbar

import com.arkivanov.decompose.ComponentContext

class RealInventoryMainToolbarComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit
) : ComponentContext by componentContext, InventoryMainToolbarComponent {
    override fun onBackClick() = this.onBackClick.invoke()
}