package com.flyview.inventory_feature.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.flyview.inventory_feature.ui.details.DocumentDetailsUi
import com.flyview.inventory_feature.ui.list.DocumentListUi
import com.flyview.inventory_feature.ui.main.MainUi
import com.flyview.inventory_feature.ui.product_edit.ProductEditUi
import com.flyview.inventory_feature.ui.test.TestCameraUi

@Composable
fun InventoryRootUi(
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
            is InventoryRootComponent.Child.Details -> DocumentDetailsUi(child.component)
            is InventoryRootComponent.Child.Edit -> ProductEditUi(child.component)
            is InventoryRootComponent.Child.List -> DocumentListUi(child.component)
            is InventoryRootComponent.Child.Main -> MainUi(child.component)
            is InventoryRootComponent.Child.TestCamera -> TestCameraUi(child.component)
        }
    }
}