package com.flyview.documents_feature.data.api

import com.flyview.documents_feature.domain.api.DocumentsApi
import com.flyview.documents_feature.domain.response.ArticulResponse
import com.flyview.documents_feature.domain.response.BarcodeResponse
import com.flyview.documents_feature.domain.response.CertificateResponse
import com.flyview.documents_feature.domain.response.DocumentResponse
import kotlinx.datetime.LocalDate

class RealDocumentsApi : DocumentsApi {
    override suspend fun getArticuls(document: Int): List<ArticulResponse> {
        TODO("Not yet implemented getArticuls")
    }

    override suspend fun getBarcodes(document: Int): List<BarcodeResponse> {
        TODO("Not yet implemented getBarcodes")
    }

    override suspend fun getCertificates(document: Int): List<CertificateResponse> {
        TODO("Not yet implemented getCertificates")
    }

    override suspend fun getDocument(id: Int): DocumentResponse {
        TODO("Not yet implemented getDocument")
    }

    override suspend fun getDocuments(date: LocalDate): List<DocumentResponse> {
        TODO("Not yet implemented getDocuments")
    }
}