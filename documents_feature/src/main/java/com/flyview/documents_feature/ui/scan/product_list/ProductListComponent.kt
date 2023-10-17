package com.flyview.documents_feature.ui.scan.product_list

import com.flyview.documents_feature.ui.scan.product_list.toolbar.ProductListToolbarComponent

interface ProductListComponent {

    val toolbarComponent: ProductListToolbarComponent
    //val viewState: StateFlow<ProductListViewState>
}