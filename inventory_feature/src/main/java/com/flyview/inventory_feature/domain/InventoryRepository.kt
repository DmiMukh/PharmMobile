package com.flyview.inventory_feature.domain

import androidx.paging.PagingData
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    suspend fun createDocument(): Document
    suspend fun deleteProduct(product: Product, documentId: Long)
    suspend fun getProduct(code: String, documentId: Long, marked: Boolean): Product
    fun getDocumentsPager(): Flow<PagingData<Document>>
    fun getProductsPager(documentId: Long): Flow<PagingData<Product>>
    suspend fun saveDocument(document: Document, products: List<Product>)
    suspend fun uploadData(stock: Int, scope: CoroutineScope)
    suspend fun upsertProduct(product: Product, documentId: Long, newQuantity: Double)

}