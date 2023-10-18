package com.flyview.documents_feature.domain

import androidx.paging.PagingData
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.model.MarkCode
import com.flyview.documents_feature.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface DocumentsRepository {

    suspend fun getDocuments(date: LocalDate): List<Document>
    fun getMarksPager(documentId: Long): Flow<PagingData<MarkCode>>
    suspend fun getProducts(document: Long): List<Product>
    fun getProductsPager(documentId: Long): Flow<PagingData<Product>>
}