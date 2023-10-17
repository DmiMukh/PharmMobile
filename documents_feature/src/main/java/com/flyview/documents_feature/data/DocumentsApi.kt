package com.flyview.documents_feature.data

import com.flyview.documents_feature.domain.response.DocumentResponse
import kotlinx.datetime.LocalDate

interface DocumentsApi {

    fun getDocument(id: Int): DocumentResponse
    fun getDocuments(date: LocalDate): List<DocumentResponse>
}