package com.flyview.inventory_feature.data

interface InventoryApi {
    suspend fun getArticuls()
    suspend fun getBarcodes()
    suspend fun getCertificates()
    suspend fun getMarks()

    suspend fun putDocument()
}