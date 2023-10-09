package com.flyview.mark_feature.ui.placement

import com.arkivanov.decompose.ComponentContext
import com.flyview.mark_feature.ui.placement.toolbar.RealPlacementToolbarComponent

class RealPlacementComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, PlacementComponent {

    override val toolbarComponent = RealPlacementToolbarComponent(
        componentContext = componentContext
    )
}