package com.flyview.inventory_feature.data

import com.flyview.inventory_feature.domain.request.DocumentRequest
import com.flyview.inventory_feature.domain.response.ArticulResponse
import com.flyview.inventory_feature.domain.response.CertificateResponse
import com.flyview.inventory_feature.domain.response.MarkResponse

interface InventoryApi {
    suspend fun getArticuls(limit: Long, offset: Long): List<ArticulResponse>
    suspend fun getCertificates(limit: Long, offset: Long, stock: Int): List<CertificateResponse>
    suspend fun getMarks(limit: Long, offset: Long, firm: Int, stock: Int): List<MarkResponse>
    suspend fun putDocument(document: DocumentRequest): Long
}