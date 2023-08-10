package com.flyview.pharmmobile.features.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.flyview.inventory_feature.ui.InventoryRootUi
import com.flyview.pharmmobile.features.home.HomeUi
import com.flyview.pharmmobile.features.settings.SettingsUi
import com.flyview.pharmmobile.features.splash.SplashUi

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack = component.childStack.collectAsState()

    Children(
        stack = childStack.value,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Home -> HomeUi(child.component)
            is RootComponent.Child.InventoryRoot -> InventoryRootUi(child.component)
            is RootComponent.Child.Settings -> SettingsUi(child.component)
            is RootComponent.Child.Splash -> SplashUi(child.component)
        }
    }
}