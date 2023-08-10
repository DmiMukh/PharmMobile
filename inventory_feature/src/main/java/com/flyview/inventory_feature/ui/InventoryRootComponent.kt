package com.flyview.inventory_feature.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.inventory_feature.ui.details.DocumentDetailsComponent
import com.flyview.inventory_feature.ui.list.DocumentListComponent
import com.flyview.inventory_feature.ui.main.MainComponent
import kotlinx.coroutines.flow.StateFlow

interface InventoryRootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

        class Details(val component: DocumentDetailsComponent): Child
        class List(val component: DocumentListComponent) : Child
        class Main(val component: MainComponent) : Child
    }
}