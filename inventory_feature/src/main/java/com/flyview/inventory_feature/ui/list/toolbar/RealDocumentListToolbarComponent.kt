package com.flyview.inventory_feature.ui.list.toolbar

import com.arkivanov.decompose.ComponentContext

class RealDocumentListToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentListToolbarComponent {

    override fun onBackClick() = this.onBack.invoke()
}