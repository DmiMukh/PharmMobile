package com.flyview.documents_feature.ui.scan.product_list

import com.arkivanov.decompose.ComponentContext
import com.flyview.documents_feature.ui.scan.product_list.toolbar.RealProductListToolbarComponent

class RealProductListComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, ProductListComponent {
    override val toolbarComponent = RealProductListToolbarComponent(
        componentContext = componentContext,
        onBack = {},
        onSave = {}
    )
}