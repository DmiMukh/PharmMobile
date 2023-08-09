package com.flyview.pharmmobile.features.inventory

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.core.ComponentFactory
import com.flyview.pharmmobile.features.inventory.ui.InventoryRootComponent
import com.flyview.pharmmobile.features.inventory.ui.RealInventoryRootComponent
import com.flyview.pharmmobile.features.inventory.ui.main.InventoryMainComponent
import com.flyview.pharmmobile.features.inventory.ui.main.RealInventoryMainComponent
import org.koin.core.component.get

fun ComponentFactory.createInventoryFeatureRootComponent(
    componentContext: ComponentContext,
    onBackClick: () -> Unit
): InventoryRootComponent {
    return RealInventoryRootComponent(
        componentContext = componentContext,
        onBackClick = onBackClick,
        componentFactory = get()
    )
}

fun ComponentFactory.createInventoryFeatureMainComponent(
    componentContext: ComponentContext,
    onBackClick: () -> Unit,
    onDocumentsClick: () -> Unit
): InventoryMainComponent {
    return RealInventoryMainComponent(
        componentContext = componentContext,
        onBackClick = onBackClick,
        onDocumentsClick = onDocumentsClick
    )
}