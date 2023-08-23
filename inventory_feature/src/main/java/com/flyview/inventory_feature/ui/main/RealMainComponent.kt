package com.flyview.inventory_feature.ui.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.ui.main.dialog.MainDialogState
import com.flyview.inventory_feature.ui.main.dialog.RealMainDialogComponent
import com.flyview.inventory_feature.ui.main.toolbar.RealMainToolbarComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealMainComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onDocumentsClick: () -> Unit,
    private val repository: InventoryRepository
) : ComponentContext by componentContext, MainComponent {
    override val formattedDateOfData = MutableStateFlow("")

    override val dialogComponent = RealMainDialogComponent(
        componentContext = componentContext,
        onDismiss = {},
        repository = this.repository
    )

    override val toolbarComponent = RealMainToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack
    )

    override fun onClearDataClick() {
        TODO("Not yet implemented")

    }

    override fun onDocumentsClick() = this.onDocumentsClick.invoke()
    override fun onUploadDataClick() {
        this.dialogComponent.state.value = MainDialogState.UploadData
    }
}