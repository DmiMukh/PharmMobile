package com.flyview.mark_feature.ui.main

import com.arkivanov.decompose.ComponentContext
import com.flyview.mark_feature.ui.main.toolbar.RealMainToolbarComponent

class RealMainComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, MainComponent {

    override val toolbarComponent = RealMainToolbarComponent(
        componentContext = componentContext
    )
}