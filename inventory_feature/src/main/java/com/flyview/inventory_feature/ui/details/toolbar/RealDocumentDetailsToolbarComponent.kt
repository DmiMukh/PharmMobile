package com.flyview.inventory_feature.ui.details.toolbar

import com.arkivanov.decompose.ComponentContext

class RealDocumentDetailsToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentDetailsToolbarComponent {
    override fun onBackClick() = this.onBack.invoke()
}