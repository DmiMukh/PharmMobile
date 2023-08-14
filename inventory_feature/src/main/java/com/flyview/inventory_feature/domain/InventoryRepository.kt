package com.flyview.inventory_feature.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    suspend fun createDocument(): Document
    suspend fun getProduct(code: String): Product
    suspend fun upsertProduct(product: Product, documentId: Long)
    fun getDocumentsPager(): Flow<PagingData<Document>>
    fun getProductsPager(documentId: Long): Flow<PagingData<Product>>
}