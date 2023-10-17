package com.flyview.documents_feature.ui.placement.toolbar

import com.flyview.documents_feature.domain.model.Document

class FakePlacementToolbarComponent: PlacementToolbarComponent {
    override val document = Document()
    override fun onBackClick() = Unit
    override fun onSaveClick() = Unit
}