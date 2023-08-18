package com.flyview.inventory_feature.data

import com.flyview.inventory_feature.domain.request.DocumentRequest
import com.flyview.inventory_feature.domain.response.ArticulResponse

interface InventoryApi {
    suspend fun getArticuls(limit: Long, offset: Long): List<ArticulResponse>
    suspend fun getCertificates(limit: Long, offset: Long, stock: Int)
    suspend fun getMarks(limit: Long, offset: Long, stock: Int)
    suspend fun putDocument(document: DocumentRequest): Long
}