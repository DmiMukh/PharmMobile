package com.flyview.pharmmobile.features.inventory.ui.main

import com.flyview.pharmmobile.features.inventory.ui.main.toolbar.FakeInventoryMainToolbarComponent

class FakeInventoryMainComponent: InventoryMainComponent {
    override val toolbarComponent = FakeInventoryMainToolbarComponent()
    override fun onDocumentsClick() = Unit
}