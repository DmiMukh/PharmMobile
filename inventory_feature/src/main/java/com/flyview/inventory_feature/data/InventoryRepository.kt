package com.flyview.inventory_feature.data

import androidx.paging.PagingData
import com.flyview.inventory_feature.domain.Document
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {

    fun documentsPager(): Flow<PagingData<Document>>
}