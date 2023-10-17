package com.flyview.documents_feature.data

import com.flyview.documents_feature.domain.response.DocumentResponse
import kotlinx.datetime.LocalDate

class DocumentsApiImpl : DocumentsApi {
    override fun getDocument(id: Int): DocumentResponse {
        TODO("Not yet implemented")
    }

    override fun getDocuments(date: LocalDate): List<DocumentResponse> {
        TODO("Not yet implemented")
    }
}