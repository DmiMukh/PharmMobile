package com.flyview.inventory_feature.ui.main

import com.flyview.inventory_feature.ui.main.dialog.MainDialogState
import com.flyview.inventory_feature.ui.main.dialog.MainDialogComponent
import com.flyview.inventory_feature.ui.main.toolbar.MainToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface MainComponent {

    val formattedDateOfData: StateFlow<String>

    val dialogComponent: MainDialogComponent
    val toolbarComponent: MainToolbarComponent

    fun onClearDataClick()
    fun onDocumentsClick()
    fun onUploadDataClick()
}