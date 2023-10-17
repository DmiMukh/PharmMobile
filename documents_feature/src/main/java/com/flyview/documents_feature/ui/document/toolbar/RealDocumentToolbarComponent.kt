package com.flyview.documents_feature.ui.document.toolbar

import com.arkivanov.decompose.ComponentContext
import com.flyview.documents_feature.domain.model.Document
import kotlinx.coroutines.flow.MutableStateFlow

class RealDocumentToolbarComponent(
    componentContext: ComponentContext,
    private val currentDocument: Document,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentToolbarComponent {
    override val document = MutableStateFlow(currentDocument)

    override fun onBackClick() = this.onBack.invoke()


}