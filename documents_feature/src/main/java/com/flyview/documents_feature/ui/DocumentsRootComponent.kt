package com.flyview.documents_feature.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.documents_feature.ui.document.DocumentComponent
import com.flyview.documents_feature.ui.main.MainComponent
import com.flyview.documents_feature.ui.placement.PlacementComponent
import kotlinx.coroutines.flow.StateFlow

interface DocumentsRootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Details(val component: DocumentComponent) : Child
        class Main(val component: MainComponent) : Child
        class Placement(val component: PlacementComponent) : Child
    }
}