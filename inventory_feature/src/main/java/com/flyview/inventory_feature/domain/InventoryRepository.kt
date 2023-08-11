package com.flyview.inventory_feature.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {

    suspend fun addProduct(product: Product, documentId: Long)
    suspend fun createDocument(): Document
    suspend fun getProduct(code: String): Product
    fun getDocumentsPager(): Flow<PagingData<Document>>
    fun getProductsPager(documentId: Long): Flow<PagingData<Product>>
}