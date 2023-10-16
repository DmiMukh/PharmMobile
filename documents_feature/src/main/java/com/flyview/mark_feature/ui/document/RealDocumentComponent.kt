package com.flyview.mark_feature.ui.document

import com.arkivanov.decompose.ComponentContext
import com.flyview.mark_feature.ui.document.toolbar.RealDocumentToolbarComponent

class RealDocumentComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentComponent {

    override val toolbarComponent = RealDocumentToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack
    )
}