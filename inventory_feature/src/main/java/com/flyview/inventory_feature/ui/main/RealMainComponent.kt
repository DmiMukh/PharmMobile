package com.flyview.inventory_feature.ui.main

import com.arkivanov.decompose.ComponentContext
import com.flyview.inventory_feature.ui.main.toolbar.RealMainToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class RealMainComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onDocumentsClick: () -> Unit
) : ComponentContext by componentContext, MainComponent {
    override val formattedDateOfData = MutableStateFlow("")

    override val toolbarComponent = RealMainToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack
    )

    override fun onClearDataClick() {
        TODO("Not yet implemented")
    }

    override fun onDocumentsClick() = this.onDocumentsClick.invoke()
    override fun onUploadDataClick() {
        TODO("Not yet implemented")
    }
}