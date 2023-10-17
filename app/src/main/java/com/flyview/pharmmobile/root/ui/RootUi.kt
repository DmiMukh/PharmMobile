package com.flyview.pharmmobile.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.flyview.core.message.ui.MessageUi
import com.flyview.documents_feature.ui.DocumentsRootUi
import com.flyview.inventory_feature.ui.InventoryRootUi
import com.flyview.pharmmobile.home.HomeUi
import com.flyview.pharmmobile.settings.SettingsUi
import com.flyview.pharmmobile.splash.SplashUi
import com.flyview.pharmmobile.usb_device.UsbListUi

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
            is RootComponent.Child.DocumentsRoot -> DocumentsRootUi(child.component)
            is RootComponent.Child.Home -> HomeUi(child.component)
            is RootComponent.Child.InventoryRoot -> InventoryRootUi(child.component)
            is RootComponent.Child.Settings -> SettingsUi(child.component)
            is RootComponent.Child.Splash -> SplashUi(child.component)
            is RootComponent.Child.UsbList -> UsbListUi(child.component)
        }
    }

    MessageUi(
        component = component.messageComponent,
        modifier = modifier,
        bottomPadding = 16.dp
    )
}