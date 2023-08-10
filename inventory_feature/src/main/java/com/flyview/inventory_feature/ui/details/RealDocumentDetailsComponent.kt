package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.domain.BarcodeReader
import com.flyview.inventory_feature.ui.details.toolbar.RealDocumentDetailsToolbarComponent

class RealDocumentDetailsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val barcodeReader: BarcodeReader
) : ComponentContext by componentContext, DocumentDetailsComponent {
    override val toolbarComponent = RealDocumentDetailsToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        barcodeReader = this.barcodeReader
    )
}