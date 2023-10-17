package com.flyview.documents_feature.ui.placement.toolbar

import com.arkivanov.decompose.ComponentContext
import com.flyview.documents_feature.domain.model.Document

class RealPlacementToolbarComponent(
    componentContext: ComponentContext,
    private val currentDocument: Document,
    private val onBack: () -> Unit,
    private val onSave: () -> Unit
) : ComponentContext by componentContext, PlacementToolbarComponent {
    override val document = this.currentDocument

    override fun onBackClick() = this.onBack.invoke()
    override fun onSaveClick() = this.onSave.invoke()
}