package com.flyview.inventory_feature.domain

import androidx.paging.PagingData
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    suspend fun clearData()
    suspend fun createDocument(): Document
    suspend fun deleteProduct(product: Product, documentId: Long)
    suspend fun getProduct(code: String, documentId: Long, marked: Boolean): Product
    suspend fun getProductsByDocument(documentId: Long): List<Product>
    fun getDocuments(): List<Document>
    fun getDocumentsPager(): Flow<PagingData<Document>>
    fun getProductsPager(documentId: Long): Flow<PagingData<Product>>
    suspend fun sendDocument(document: Document, products: List<Product>): Int
    suspend fun updateDocument(document: Document, infSysId: Int)
    suspend fun uploadArticuls(): Boolean
    suspend fun uploadCertificates(): Boolean
    suspend fun uploadMarks(): Boolean
    suspend fun upsertProduct(product: Product, documentId: Long, newQuantity: Double)
}