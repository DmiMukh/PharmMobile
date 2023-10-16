package com.flyview.mark_feature.ui.placement.toolbar

import com.arkivanov.decompose.ComponentContext
import com.flyview.mark_feature.domain.model.Document

class RealPlacementToolbarComponent(
    componentContext: ComponentContext,
    private val currentDocument: Document,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, PlacementToolbarComponent {
    override val document = this.currentDocument

    override fun onBackClick() = this.onBack.invoke()
}