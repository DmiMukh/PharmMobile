package com.flyview.documents_feature.ui.placement

import com.flyview.documents_feature.domain.model.Cell
import com.flyview.documents_feature.ui.placement.navbar.FakePlacementNavbarComponent
import com.flyview.documents_feature.ui.placement.toolbar.FakePlacementToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class FakePlacementComponent : PlacementComponent {
    override val cell = MutableStateFlow(Cell(0, "1-2-1-2а"))
    override val navbarComponent = FakePlacementNavbarComponent()
    override val toolbarComponent = FakePlacementToolbarComponent()
}