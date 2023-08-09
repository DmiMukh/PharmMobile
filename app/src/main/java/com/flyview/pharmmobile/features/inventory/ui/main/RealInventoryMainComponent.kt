package com.flyview.pharmmobile.features.inventory.ui.main

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.features.inventory.ui.main.toolbar.RealInventoryMainToolbarComponent

class RealInventoryMainComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit,
    private val onDocumentsClick: () -> Unit,
): ComponentContext by componentContext, InventoryMainComponent {

    override val toolbarComponent = RealInventoryMainToolbarComponent(
        componentContext = componentContext,
        onBackClick = this.onBackClick
    )

    override fun onDocumentsClick() = this.onDocumentsClick.invoke()
}