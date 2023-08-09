package com.flyview.pharmmobile.features.inventory.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.pharmmobile.features.inventory.ui.list.InventoryDocumentListComponent
import com.flyview.pharmmobile.features.inventory.ui.main.InventoryMainComponent
import kotlinx.coroutines.flow.StateFlow

interface InventoryRootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

        class List(val component: InventoryDocumentListComponent) : Child

        class Main(val component: InventoryMainComponent) : Child
    }
}