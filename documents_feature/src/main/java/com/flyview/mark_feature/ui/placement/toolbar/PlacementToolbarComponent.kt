package com.flyview.mark_feature.ui.placement.toolbar

import com.flyview.mark_feature.domain.model.Document

interface PlacementToolbarComponent {

    val document: Document
    fun onBackClick()
}