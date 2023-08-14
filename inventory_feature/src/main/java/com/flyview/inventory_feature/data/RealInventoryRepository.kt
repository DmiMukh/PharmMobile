package com.flyview.inventory_feature.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import app.cash.sqldelight.paging3.QueryPagingSource
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.Product
import com.flyview.inventory_feature.domain.toData
import com.flyview.inventory_feature.domain.toDomain
import com.flyview.pharmmobile.inventory_feature.InventoryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class RealInventoryRepository(
    private val db: InventoryDatabase
) : InventoryRepository {
    override suspend fun createDocument(): Document {
        db.documentEntityQueries.let {
            return it.transactionWithResult {
                it.insert("")

                val documentId = it.lastInsertRowId().executeAsOne()

                it.document(id = documentId).executeAsOne()
            }.toDomain()
        }
    }

    override suspend fun getProduct(code: String): Product {
        TODO("Not yet implemented")
    }

    override suspend fun upsertProduct(product: Product, documentId: Long) {
        db.transaction {
            val item = product.toData(document = documentId)

            db.goodEntityQueries.update(
                quantity = item.quantity,
                certificate = item.certificate,
                sgtin = item.sgtin,
                document = item.document
            )

            db.goodEntityQueries.insertOrIgnore(product.toData(document = documentId))
        }
    }

    override fun getDocumentsPager() = Pager(PagingConfig(pageSize = 10)) {
        QueryPagingSource(
            countQuery = db.documentEntityQueries.countDocuments(),
            transacter = db.documentEntityQueries,
            context = Dispatchers.IO,
            queryProvider = db.documentEntityQueries::documents
        )
    }.flow.map { it.map { document -> document.toDomain() } }

    override fun getProductsPager(documentId: Long) =
        Pager(PagingConfig(pageSize = 10)) {
            QueryPagingSource(
                countQuery = db.goodEntityQueries.countGoods(document = documentId),
                transacter = db.goodEntityQueries,
                context = Dispatchers.IO,
                queryProvider = { limit, offset ->
                    db.goodEntityQueries.goods(
                        document = documentId,
                        limit = limit,
                        offset = offset
                    )
                }
            )
        }.flow.map { it.map { good -> Product() } }
}