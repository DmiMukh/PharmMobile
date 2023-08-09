package com.flyview.pharmmobile.features

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.core.ComponentFactory
import com.flyview.pharmmobile.features.home.HomeComponent
import com.flyview.pharmmobile.features.home.RealHomeComponent
import com.flyview.pharmmobile.features.settings.RealSettingsComponent
import com.flyview.pharmmobile.features.settings.SettingsComponent
import com.flyview.pharmmobile.features.splash.RealSplashComponent
import com.flyview.pharmmobile.features.splash.SplashComponent

fun ComponentFactory.createHomeComponent(
    componentContext: ComponentContext,
    onInventoryClick: () -> Unit
): HomeComponent {
    return RealHomeComponent(
        componentContext = componentContext,
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