package com.flyview.documents_feature.ui.scan.product_list

import com.flyview.documents_feature.ui.scan.product_list.toolbar.FakeProductListToolbarComponent

class FakeProductListComponent : ProductListComponent {
    override val toolbarComponent = FakeProductListToolbarComponent()
}