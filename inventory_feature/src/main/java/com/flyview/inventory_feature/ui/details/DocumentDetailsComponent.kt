package com.flyview.inventory_feature.ui.details

import androidx.paging.PagingData
import com.flyview.inventory_feature.domain.model.Product
import com.flyview.inventory_feature.ui.details.toolbar.DocumentDetailsToolbarComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface DocumentDetailsComponent {

    val idleHandleScanCode: StateFlow<Boolean>
    val productsPager: Flow<PagingData<Product>>
    val toolbarComponent: DocumentDetailsToolbarComponent

    fun onItemClick(product: Product)
    fun onHandleReadBarcode(code: String)

    fun setHandleState(idle: Boolean)
}