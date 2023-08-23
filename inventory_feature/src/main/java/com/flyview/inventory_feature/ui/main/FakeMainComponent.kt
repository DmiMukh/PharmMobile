package com.flyview.inventory_feature.ui.main

import com.flyview.inventory_feature.ui.main.dialog.FakeMainDialogComponent
import com.flyview.inventory_feature.ui.main.dialog.MainDialogState
import com.flyview.inventory_feature.ui.main.toolbar.FakeMainToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMainComponent : MainComponent {

    override val formattedDateOfData = MutableStateFlow("01.01.2023")
    override val dialogComponent = FakeMainDialogComponent()
    override val toolbarComponent = FakeMainToolbarComponent()
    override fun onClearDataClick() = Unit
    override fun onDocumentsClick() = Unit
    override fun onUploadDataClick() = Unit
}