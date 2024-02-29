package com.flyview.inventory_feature.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.inventory_feature.ui.details.DocumentDetailsComponent
import com.flyview.inventory_feature.ui.list.DocumentListComponent
import com.flyview.inventory_feature.ui.main.MainComponent
import com.flyview.inventory_feature.ui.product_edit.ProductEditComponent
import com.flyview.inventory_feature.ui.test.TestCameraComponent
import kotlinx.coroutines.flow.StateFlow

interface InventoryRootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

        class Details(val component: DocumentDetailsComponent): Child
        class Edit(val component: ProductEditComponent) : Child
        class List(val component: DocumentListComponent) : Child
        class Main(val component: MainComponent) : Child

        class TestCamera(val component: TestCameraComponent) : Child
    }
}