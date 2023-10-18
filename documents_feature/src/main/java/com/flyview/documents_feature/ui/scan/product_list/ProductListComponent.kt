package com.flyview.documents_feature.ui.scan.product_list

import androidx.paging.PagingData
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.ui.scan.product_list.toolbar.ProductListToolbarComponent
import kotlinx.coroutines.flow.Flow

interface ProductListComponent {

    val productsPager: Flow<PagingData<Product>>
    val toolbarComponent: ProductListToolbarComponent

    fun onItemClick(model: Product)
}