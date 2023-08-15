package com.flyview.inventory_feature.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    suspend fun createDocument(): Document
    suspend fun deleteProduct(product: Product, documentId: Long)
    suspend fun getProduct(code: String, documentId: Long, marked: Boolean): Product
    suspend fun upsertProduct(product: Product, documentId: Long, newQuantity: Double)
    fun getDocumentsPager(): Flow<PagingData<Document>>
    fun getProductsPager(documentId: Long): Flow<PagingData<Product>>
}