package com.flyview.documents_feature.ui.placement

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.barcode.data.BarcodeReaderData
import com.flyview.core.utils.componentScope
import com.flyview.documents_feature.domain.model.Cell
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.placement.navbar.RealPlacementNavbarComponent
import com.flyview.documents_feature.ui.placement.toolbar.RealPlacementToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealPlacementComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, PlacementComponent {

    override val cell = MutableStateFlow(Cell(-1, ""))

    override val navbarComponent = RealPlacementNavbarComponent(
        componentContext = componentContext
    )

    override val toolbarComponent = RealPlacementToolbarComponent(
        componentContext = componentContext,
        currentDocument = Document(),
        onBack = this.onBack,
        onSave = { }
    )

    private fun onReadBarcode(code: String) = componentScope.launch {
        TODO("Обработка кода")
    }

    init {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(componentScope)
    }
}