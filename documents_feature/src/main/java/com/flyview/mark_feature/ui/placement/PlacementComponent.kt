package com.flyview.mark_feature.ui.placement

import com.flyview.mark_feature.domain.model.Cell
import com.flyview.mark_feature.ui.placement.toolbar.PlacementToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface PlacementComponent {

    val cell: StateFlow<Cell>
    val toolbarComponent: PlacementToolbarComponent
}