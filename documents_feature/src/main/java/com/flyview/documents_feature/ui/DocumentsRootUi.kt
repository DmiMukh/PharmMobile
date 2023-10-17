package com.flyview.documents_feature.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.flyview.documents_feature.ui.document.DocumentUi
import com.flyview.documents_feature.ui.main.MainUi
import com.flyview.documents_feature.ui.placement.PlacementUi

@Composable
fun DocumentsRootUi(
    component: DocumentsRootComponent,
    modifier: Modifier = Modifier
) {
    val childStack = component.childStack.collectAsState()

    Children(
        stack = childStack.value,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is DocumentsRootComponent.Child.Details -> DocumentUi(child.component)
            is DocumentsRootComponent.Child.Main -> MainUi(child.component)
            is DocumentsRootComponent.Child.Placement -> PlacementUi(child.component)
        }
    }
}