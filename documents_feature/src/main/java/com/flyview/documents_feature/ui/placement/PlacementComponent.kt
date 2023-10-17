package com.flyview.documents_feature.ui.placement

import com.flyview.documents_feature.domain.model.Cell
import com.flyview.documents_feature.ui.placement.navbar.PlacementNavbarComponent
import com.flyview.documents_feature.ui.placement.toolbar.PlacementToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface PlacementComponent {

    val cell: StateFlow<Cell>
    val navbarComponent: PlacementNavbarComponent
    val toolbarComponent: PlacementToolbarComponent
}