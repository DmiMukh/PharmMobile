package com.flyview.inventory_feature.ui.main

import com.flyview.inventory_feature.ui.main.toolbar.FakeMainToolbarComponent

class FakeMainComponent: MainComponent {
    override val toolbarComponent = FakeMainToolbarComponent()
    override fun onDocumentsClick() = Unit
}