package com.flyview.pharmmobile.features.inventory.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.features.inventory.ui.details.toolbar.RealInventoryDocumentDetailsToolbarComponent

class RealInventoryDocumentDetailsComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext, InventoryDocumentDetailsComponent {

    override val toolbarComponent = RealInventoryDocumentDetailsToolbarComponent(
        componentContext = componentContext
    )

}