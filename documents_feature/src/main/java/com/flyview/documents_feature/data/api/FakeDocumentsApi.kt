package com.flyview.documents_feature.data.api

import com.flyview.documents_feature.domain.api.DocumentsApi
import com.flyview.documents_feature.domain.response.ArticulResponse
import com.flyview.documents_feature.domain.response.BarcodeResponse
import com.flyview.documents_feature.domain.response.CertificateResponse
import com.flyview.documents_feature.domain.response.DocumentResponse
import kotlinx.datetime.LocalDate

class FakeDocumentsApi: DocumentsApi {
    override suspend fun getArticuls(document: Int): List<ArticulResponse> {

        val items = mutableListOf<ArticulResponse>()
        for (i in 0..4) {
            items.add(
                ArticulResponse(id = i)
            )
        }
        return items
    }

    override suspend fun getBarcodes(document: Int): List<BarcodeResponse> {
        val items = mutableListOf<BarcodeResponse>()
        for (i in 0..4) {
            items.add(
                BarcodeResponse(articul = i)
            )
        }
        return items
    }

    override suspend fun getCertificates(document: Int): List<CertificateResponse> {
        val items = mutableListOf<CertificateResponse>()
        for (i in 0..4) {
            items.add(
                CertificateResponse(id = i)
            )
        }
        return items
    }

    override suspend fun getDocument(id: Int): DocumentResponse {
        return DocumentResponse(id = id)
    }

    override suspend fun getDocuments(date: LocalDate): List<DocumentResponse> {
        return listOf(
            DocumentResponse(1),
            DocumentResponse(2)
        )
    }
}