package com.flyview.inventory_feature.domain

import androidx.paging.PagingData
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.model.Mark
import com.flyview.inventory_feature.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    suspend fun clearData()
    suspend fun createDocument(): Document
    suspend fun deleteProduct(product: Product, documentId: Long)
    suspend fun getProduct(code: String, documentId: Long, marked: Boolean): Product
    suspend fun getProductsByDocument(documentId: Long): List<Product>
    suspend fun getMarksByDocument(documentId: Long) : List<Mark>
    fun getDocumentsPager(): Flow<PagingData<Document>>
    fun getProductsPager(documentId: Long): Flow<PagingData<Product>>
    suspend fun sendDocument(document: Document, products: List<Product>, marks: List<Mark>)
    suspend fun uploadData()
    suspend fun uploadArticuls(): Boolean
    suspend fun uploadCertificates(): Boolean
    suspend fun uploadMarks(): Boolean
    suspend fun upsertProduct(product: Product, documentId: Long, newQuantity: Double)
}