package com.flyview.documents_feature.ui.document

import com.arkivanov.decompose.ComponentContext
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.document.toolbar.RealDocumentToolbarComponent

class RealDocumentComponent(
    componentContext: ComponentContext,
    private val currentDocument: Document,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentComponent {

    override val toolbarComponent = RealDocumentToolbarComponent(
        componentContext = componentContext,
        currentDocument = this.currentDocument,
        onBack = this.onBack
    )

    override fun onScanClick() {
        TODO("Not yet implemented")
    }

    override fun onPlacementClick() {
        TODO("Not yet implemented")
    }
}