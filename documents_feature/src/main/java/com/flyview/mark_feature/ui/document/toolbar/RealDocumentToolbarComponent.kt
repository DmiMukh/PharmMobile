package com.flyview.mark_feature.ui.document.toolbar

import com.arkivanov.decompose.ComponentContext

class RealDocumentToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentToolbarComponent {
    override fun onBackClick() = this.onBack.invoke()


}