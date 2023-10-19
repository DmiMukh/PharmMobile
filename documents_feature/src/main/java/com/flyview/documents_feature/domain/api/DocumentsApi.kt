package com.flyview.documents_feature.domain.api

import com.flyview.documents_feature.domain.response.ArticulResponse
import com.flyview.documents_feature.domain.response.BarcodeResponse
import com.flyview.documents_feature.domain.response.CertificateResponse
import com.flyview.documents_feature.domain.response.DocumentResponse
import kotlinx.datetime.LocalDate

interface DocumentsApi {

    suspend fun getArticuls(document: Int): List<ArticulResponse>
    suspend fun getBarcodes(document: Int): List<BarcodeResponse>
    suspend fun getCertificates(document: Int): List<CertificateResponse>
    suspend fun getDocument(id: Int): DocumentResponse
    suspend fun getDocuments(date: LocalDate): List<DocumentResponse>
}