package com.flyview.inventory_feature

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.inventory_feature.ui.RealInventoryRootComponent
import com.flyview.inventory_feature.ui.InventoryRootComponent

fun ComponentFactory.createInventoryComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): InventoryRootComponent {
    return RealInventoryRootComponent(
        componentContext = componentContext,
        onBack = onBack
    )
}