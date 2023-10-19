package com.flyview.documents_feature.domain.repository

import app.cash.paging.PagingData
import com.flyview.core.barcode.data.Barcode
import com.flyview.documents_feature.domain.model.Cell
import com.flyview.documents_feature.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface PlacementRepository {
    suspend fun collectProduct(cell: Cell, product: Product, quantity: Double)
    suspend fun deleteProduct(cell: Cell, product: Product)
    suspend fun editProduct(cell: Cell, product: Product, quantity: Double)
    suspend fun getCell(barcode: String): Cell?
    suspend fun getCollectedProducts(): Flow<PagingData<Product>>
    suspend fun getFreeProducts(): Flow<PagingData<Product>>
    suspend fun getProduct(cell: Cell, barcode: Barcode): Product?
}