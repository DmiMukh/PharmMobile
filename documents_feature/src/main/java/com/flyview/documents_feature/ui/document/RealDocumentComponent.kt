package com.flyview.documents_feature.ui.document

import com.arkivanov.decompose.ComponentContext
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.document.toolbar.RealDocumentToolbarComponent

class RealDocumentComponent(
    componentContext: ComponentContext,
    private val currentDocument: Document,
    private val onBack: () -> Unit,
    private val onScanClick: () -> Unit,
    private val onPlacementClick: () -> Unit
    ) : ComponentContext by componentContext, DocumentComponent {

    override val toolbarComponent = RealDocumentToolbarComponent(
        componentContext = componentContext,
        currentDocument = this.currentDocument,
        onBack = this.onBack
    )

    override fun onScanClick() = this.onScanClick.invoke()
    override fun onPlacementClick() = this.onPlacementClick.invoke()
}