package com.flyview.documents_feature.data.repository

import androidx.paging.map
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.sqldelight.paging3.QueryPagingSource
import com.flyview.core.barcode.data.Barcode
import com.flyview.documents_feature.domain.model.Cell
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.domain.repository.PlacementRepository
import com.flyview.pharmmobile.documents_feature.DocDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class PlacementRepositoryImpl(
    private val db: DocDatabase
) : PlacementRepository {
    override suspend fun collectProduct(cell: Cell, product: Product, quantity: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(cell: Cell, product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun editProduct(cell: Cell, product: Product, quantity: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun getCell(barcode: String): Cell? {
        TODO("Not yet implemented")
    }

    override suspend fun getCollectedProducts() = Pager(PagingConfig(pageSize = 10)) {
        QueryPagingSource(
            countQuery = db.productEntityQueries.countCollectedProducts(),
            transacter = db.productEntityQueries,
            context = Dispatchers.IO,
            queryProvider = db.productEntityQueries::collectedProducts
        )
    }.flow.map { it.map { item -> Product() } }

    override suspend fun getFreeProducts() = Pager(PagingConfig(pageSize = 10)) {
        QueryPagingSource(
            countQuery = db.productEntityQueries.countFreeProducts(),
            transacter = db.productEntityQueries,
            context = Dispatchers.IO,
            queryProvider = db.productEntityQueries::freeProducts
        )
    }.flow.map { it.map { item -> Product() } }

    override suspend fun getProduct(cell: Cell, barcode: Barcode): Product? {
        TODO("Not yet implemented")
    }
}