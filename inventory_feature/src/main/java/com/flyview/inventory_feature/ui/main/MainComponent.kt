package com.flyview.inventory_feature.ui.main

import com.flyview.inventory_feature.ui.main.toolbar.MainToolbarComponent

interface MainComponent {

    val toolbarComponent: MainToolbarComponent

    fun onDocumentsClick()
}