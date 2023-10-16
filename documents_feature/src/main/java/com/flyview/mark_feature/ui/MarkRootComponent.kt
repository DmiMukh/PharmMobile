package com.flyview.mark_feature.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.mark_feature.ui.main.MainComponent
import kotlinx.coroutines.flow.StateFlow

interface MarkRootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Main(val component: MainComponent) : Child
    }
}