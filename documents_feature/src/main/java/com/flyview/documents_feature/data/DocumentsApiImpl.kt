package com.flyview.documents_feature.data

import com.flyview.documents_feature.domain.response.ArticulResponse
import com.flyview.documents_feature.domain.response.BarcodeResponse
import com.flyview.documents_feature.domain.response.CertificateResponse
import com.flyview.documents_feature.domain.response.DocumentResponse
import kotlinx.datetime.LocalDate

class DocumentsApiImpl : DocumentsApi {
    override suspend fun getArticuls(document: Int): List<ArticulResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getBarcodes(document: Int): List<BarcodeResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getCertificates(document: Int): List<CertificateResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getDocument(id: Int): DocumentResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getDocuments(date: LocalDate): List<DocumentResponse> {
        TODO("Not yet implemented")
    }
}