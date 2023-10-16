package com.flyview.mark_feature.ui.placement.toolbar

import com.flyview.mark_feature.domain.model.Document

class FakePlacementToolbarComponent: PlacementToolbarComponent {
    override val document = Document()
    override fun onBackClick() = Unit
}