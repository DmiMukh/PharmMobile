package com.flyview.pharmmobile.features.inventory.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.flyview.pharmmobile.features.inventory.ui.list.InventoryDocumentListUi
import com.flyview.pharmmobile.features.inventory.ui.main.InventoryMainUi

@Composable
fun InventoryRootContent(
    component: InventoryRootComponent,
    modifier: Modifier = Modifier
) {
    val childStack = component.childStack.collectAsState()

    Children(
        stack = childStack.value,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is InventoryRootComponent.Child.List -> InventoryDocumentListUi(child.component)
            is InventoryRootComponent.Child.Main -> InventoryMainUi(child.component)
        }
    }
}