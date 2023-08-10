package com.flyview.pharmmobile

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.inventory_feature.inventoryModule
import com.flyview.pharmmobile.home.HomeComponent
import com.flyview.pharmmobile.home.RealHomeComponent
import com.flyview.pharmmobile.settings.RealSettingsComponent
import com.flyview.pharmmobile.settings.SettingsComponent
import com.flyview.pharmmobile.splash.RealSplashComponent
import com.flyview.pharmmobile.splash.SplashComponent

val allModules = listOf(
    inventoryModule
)

fun ComponentFactory.createHomeComponent(
    componentContext: ComponentContext,
    onBarcodeReaderClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onInventoryClick: () -> Unit
): HomeComponent {
    return RealHomeComponent(
        componentContext = componentContext,
        onBarcodeReaderClick = onBarcodeReaderClick,
        onSettingsClick = onSettingsClick,
        onInventoryClick = onInventoryClick
    )
}

fun ComponentFactory.createSettingsComponent(
    componentContext: ComponentContext,
    onBackClick: () -> Unit
): SettingsComponent {
    return RealSettingsComponent(
        componentContext = componentContext,
        onBackClick = onBackClick
    )
}

fun ComponentFactory.createSplashComponent(
    componentContext: ComponentContext,
    onFinish: () -> Unit
): SplashComponent {
    return RealSplashComponent(
        componentContext = componentContext,
        onFinish = onFinish
    )
}