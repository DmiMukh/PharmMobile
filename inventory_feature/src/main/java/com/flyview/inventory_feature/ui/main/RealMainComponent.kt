package com.flyview.inventory_feature.ui.main

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.ui.main.toolbar.RealMainToolbarComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealMainComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onDocumentsClick: () -> Unit,
    private val repository: InventoryRepository
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

        componentCoroutineScope().launch {
            repository.uploadData(
                156,
                this
            )
        }
    }
}