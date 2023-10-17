package com.flyview.documents_feature.ui.document.toolbar

import com.flyview.documents_feature.domain.model.Document
import kotlinx.coroutines.flow.StateFlow

interface DocumentToolbarComponent {

    val document: StateFlow<Document>
    fun onBackClick()
}