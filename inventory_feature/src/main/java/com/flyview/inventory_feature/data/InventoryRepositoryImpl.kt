package com.flyview.inventory_feature.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import app.cash.sqldelight.paging3.QueryPagingSource
import com.flyview.core.storage.SettingsStorage
import com.flyview.core.utils.getCurrentLocalDateTime
import com.flyview.inventory_feature.domain.AGENT
import com.flyview.inventory_feature.domain.FIRM
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.STOCK
import com.flyview.inventory_feature.domain.model.Mark
import com.flyview.inventory_feature.domain.model.Product
import com.flyview.inventory_feature.domain.model.toData
import com.flyview.inventory_feature.domain.model.toDomain
import com.flyview.inventory_feature.domain.model.toGoodRequest
import com.flyview.inventory_feature.domain.model.toRequest
import com.flyview.inventory_feature.domain.request.DocumentRequest
import com.flyview.inventory_feature.domain.response.ArticulResponse
import com.flyview.inventory_feature.domain.response.CertificateResponse
import com.flyview.inventory_feature.domain.response.MarkResponse
import com.flyview.inventory_feature.domain.response.toEntity
import com.flyview.pharmmobile.inventory_feature.InventoryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import java.util.UUID

class InventoryRepositoryImpl(
    private val db: InventoryDatabase,
    private val api: InventoryApi,
    private val storage: SettingsStorage
) : InventoryRepository {
    override suspend fun createDocument(): Document {
        db.documentEntityQueries.let {

            val currentDateTime = getCurrentLocalDateTime()

            return it.transactionWithResult {
                it.insert(
                    number = "",
                    creation_date = currentDateTime.toString(),
                    guid = UUID.randomUUID().toString(),
                    is_id = -1
                )

                val documentId = it.lastInsertRowId().executeAsOne()

                it.document(id = documentId).executeAsOne()
            }.toDomain()
        }
    }

    override suspend fun deleteProduct(product: Product, documentId: Long) {
        db.goodEntityQueries.delete(
            certificate = product.certificate.id,
            sgtin = product.sgtin,
            document = documentId
        )
    }

    override suspend fun getProduct(code: String, documentId: Long, marked: Boolean): Product {
        if (marked) {
            db.transactionWithResult {
                db.productEntityQueries.selectBySgtin(document = documentId, sgtin = code)
                    .executeAsOneOrNull()
            }?.let {
                return it.toDomain(code)
            }
        } else {
            db.transactionWithResult {
                db.productEntityQueries
                    .selectByBarcode(document = documentId, barcode = code)
                    .executeAsOneOrNull()
            }?.let {
                return it.toDomain("")
            }
        }

        return Product()
    }

    override suspend fun upsertProduct(product: Product, documentId: Long, newQuantity: Double) {
        db.transaction {
            val item = product.toData(document = documentId, quantity = newQuantity)

            db.goodEntityQueries.update(
                quantity = newQuantity,
                certificate = item.certificate,
                sgtin = item.sgtin,
                document = item.document
            )

            db.goodEntityQueries.insertOrIgnore(item)
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

    override fun getProductsPager(documentId: Long) = Pager(PagingConfig(pageSize = 10)) {
        QueryPagingSource(
            countQuery = db.goodEntityQueries.countGoods(document = documentId),
            transacter = db.goodEntityQueries,
            context = Dispatchers.IO,
            queryProvider = { limit, offset ->
                db.goodEntityQueries.selectProductsByDocument(
                    document = documentId,
                    limit = limit,
                    offset = offset
                )
            }
        )
    }.flow.map { it.map { good -> good.toDomain() } }

    override suspend fun saveDocument(
        document: Document,
        products: List<Product>,
        marks: List<Mark>
    ) {
        val firm = storage.getInt(FIRM)
        val stock = storage.getInt(STOCK)
        val partner = storage.getInt(AGENT)

        val data = DocumentRequest(
            expectedDate = getCurrentLocalDateTime().toString(),
            firm = firm,
            heap = "",
            id = -1,
            opDate = getCurrentLocalDateTime().toString(),
            partner = partner,
            stock = stock,
            products = products.map { it.toGoodRequest() },
            marks = marks.map { it.toRequest() }
        )

        val documentId = api.putDocument(data)

    }

    override suspend fun uploadData() {

        val stock = storage.getInt(STOCK)

        var offset = 0L

        var articuls: List<ArticulResponse>?
        do {
            articuls = api.getArticuls(limit = 10_000, offset = offset)

            db.articulEntityQueries.let { query ->
                query.transaction { articuls.forEach { item -> query.insert(item.toEntity()) } }
            }


            offset = offset.plus(10_000)
        } while (articuls.isNullOrEmpty())


        var certificates: List<CertificateResponse>?
        offset = 0
        do {
            certificates = api.getCertificates(limit = 10_000, offset = offset, stock = stock)

            db.certificateEntityQueries.let { query ->
                query.transaction { certificates.forEach { item -> query.insert(item.toEntity()) } }
            }

            offset = offset.plus(10_000)

        } while (certificates.isNullOrEmpty())

        var marks: List<MarkResponse>?
        offset = 0
        do {
            marks = api.getMarks(limit = 10_000, offset = offset, stock = stock)

            db.markEntityQueries.let { query ->
                query.transaction { marks.forEach { item -> query.insert(item.toEntity()) } }
            }

        } while (marks.isNullOrEmpty())
    }
}