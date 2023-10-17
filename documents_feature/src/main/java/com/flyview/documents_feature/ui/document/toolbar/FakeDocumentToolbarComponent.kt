package com.flyview.documents_feature.ui.document.toolbar

import com.flyview.documents_feature.domain.model.Document
import kotlinx.coroutines.flow.MutableStateFlow

class FakeDocumentToolbarComponent : DocumentToolbarComponent {
    override val document = MutableStateFlow(Document())
    override fun onBackClick() = Unit
}