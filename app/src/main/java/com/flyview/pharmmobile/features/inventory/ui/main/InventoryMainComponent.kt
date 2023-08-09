package com.flyview.pharmmobile.features.inventory.ui.main

import com.flyview.pharmmobile.features.inventory.ui.main.toolbar.InventoryMainToolbarComponent

interface InventoryMainComponent {

    val toolbarComponent: InventoryMainToolbarComponent

    fun onDocumentsClick()
}