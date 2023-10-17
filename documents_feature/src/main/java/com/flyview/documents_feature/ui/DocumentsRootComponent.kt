package com.flyview.documents_feature.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.documents_feature.ui.document.DocumentComponent
import com.flyview.documents_feature.ui.main.MainComponent
import com.flyview.documents_feature.ui.placement.PlacementComponent
import com.flyview.documents_feature.ui.scan.mark_list.MarkListComponent
import com.flyview.documents_feature.ui.scan.product_list.ProductListComponent
import kotlinx.coroutines.flow.StateFlow

interface DocumentsRootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Details(val component: DocumentComponent) : Child
        class Main(val component: MainComponent) : Child
        class MarkList(val component: MarkListComponent) : Child
        class Placement(val component: PlacementComponent) : Child
        class ProductList(val component: ProductListComponent) : Child
    }
}