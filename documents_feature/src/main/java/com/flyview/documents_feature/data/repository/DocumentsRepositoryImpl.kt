package com.flyview.documents_feature.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.sqldelight.paging3.QueryPagingSource
import com.flyview.documents_feature.domain.api.DocumentsApi
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.model.MarkCode
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.domain.repository.DocumentsRepository
import com.flyview.pharmmobile.documents_feature.DocDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

class DocumentsRepositoryImpl(
    private val api: DocumentsApi,
    private val db: DocDatabase
): DocumentsRepository {
    override suspend fun getDocuments(date: LocalDate): List<Document> {
        api.getDocuments(date)
        TODO("Not yet implemented getDocuments")
    }

    override fun getMarksPager(documentId: Long): Flow<PagingData<MarkCode>> {
        TODO("Not yet implemented getMarksPager")
    }

    override suspend fun getProducts(document: Long): List<Product> {
        TODO("Not yet implemented getProducts")
    }

    override fun getProductsPager() = Pager(PagingConfig(pageSize = 200)) {
        QueryPagingSource(
            countQuery = db.productEntityQueries.countProducts(),
            transacter = db.productEntityQueries,
            context = Dispatchers.IO,
            queryProvider = db.productEntityQueries::products
        )
    }.flow.map { it.map { item -> Product() } }
}