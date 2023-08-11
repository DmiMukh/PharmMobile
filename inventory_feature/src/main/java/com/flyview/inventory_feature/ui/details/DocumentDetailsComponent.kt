package com.flyview.inventory_feature.ui.details

import androidx.paging.PagingData
import com.flyview.inventory_feature.domain.Product
import com.flyview.inventory_feature.ui.details.toolbar.DocumentDetailsToolbarComponent
import kotlinx.coroutines.flow.Flow

interface DocumentDetailsComponent {

    val productsPager: Flow<PagingData<Product>>
    val toolbarComponent: DocumentDetailsToolbarComponent

    fun onItemClick(product: Product)
}