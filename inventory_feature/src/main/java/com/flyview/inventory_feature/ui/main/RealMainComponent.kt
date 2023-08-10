package com.flyview.inventory_feature.ui.main

import com.arkivanov.decompose.ComponentContext

class RealMainComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, MainComponent {

}