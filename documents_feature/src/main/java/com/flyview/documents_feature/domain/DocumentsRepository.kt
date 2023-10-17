package com.flyview.documents_feature.domain

import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.model.Product
import kotlinx.datetime.LocalDate

interface DocumentsRepository {


    suspend fun getProducts(document: Long): List<Product>
    suspend fun getDocuments(date: LocalDate): List<Document>
}