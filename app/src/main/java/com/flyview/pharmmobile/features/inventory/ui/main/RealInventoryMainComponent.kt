package com.flyview.pharmmobile.features.inventory.ui.main

import com.arkivanov.decompose.ComponentContext

class RealInventoryMainComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit,
    private val onDocumentsClick: () -> Unit,
): ComponentContext by componentContext, InventoryMainComponent {

}