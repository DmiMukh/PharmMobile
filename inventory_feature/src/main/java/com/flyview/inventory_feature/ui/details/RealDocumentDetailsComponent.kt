package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.inventory_feature.ui.details.toolbar.RealDocumentDetailsToolbarComponent

class RealDocumentDetailsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentDetailsComponent {
    override val toolbarComponent = RealDocumentDetailsToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack
    )
}