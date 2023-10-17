package com.flyview.documents_feature.ui.placement.toolbar

import com.flyview.documents_feature.domain.model.Document

interface PlacementToolbarComponent {

    val document: Document
    fun onBackClick()
    fun onSaveClick()
}