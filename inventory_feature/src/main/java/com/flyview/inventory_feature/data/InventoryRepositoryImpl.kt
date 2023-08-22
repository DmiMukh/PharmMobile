package com.flyview.inventory_feature.data

import android.os.Build
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import app.cash.sqldelight.paging3.QueryPagingSource
import com.flyview.core.utils.getCurrentLocalDateTime
import com.flyview.core.utils.toLocalDateTime
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.model.Mark
import com.flyview.inventory_feature.domain.model.Product
import com.flyview.inventory_feature.domain.model.toData
import com.flyview.inventory_feature.domain.model.toDomain
import com.flyview.inventory_feature.domain.model.toGoodRequest
import com.flyview.inventory_feature.domain.model.toRequest
import com.flyview.inventory_feature.domain.request.DocumentRequest
import com.flyview.inventory_feature.domain.response.ArticulResponse
import com.flyview.inventory_feature.domain.response.CertificateResponse
import com.flyview.inventory_feature.domain.response.toEntity
import com.flyview.inventoryfeature.ArticulEntity
import com.flyview.inventoryfeature.BarcodeEntity
import com.flyview.inventoryfeature.CertificateEntity
import com.flyview.pharmmobile.inventory_feature.InventoryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalDateTime
import kotlinx.datetime.todayIn
import java.util.Calendar
import java.util.UUID

class InventoryRepositoryImpl(
    private val db: InventoryDatabase,
    private val api: InventoryApi
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
        val firm = 50
        val stock = 156
        val partner = 20092

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

    override suspend fun uploadData(stock: Int) {
        /*
        val articuls = scope.async {
            var items: List<ArticulResponse>?
            var offset = 0L
            do {
                items = api.getArticuls(limit = 10_000, offset = offset)

                db.articulEntityQueries.let { query ->
                    query.transaction { items?.forEach { item -> query.insert(item.toEntity()) } }
                }


                offset = offset.plus(10_000)
            } while (items.isNullOrEmpty())
        }

        val certificates = scope.async {
            var items: List<CertificateResponse>?
            var offset = 0L
            do {
                items = api.getCertificates(limit = 10_000, offset = offset, stock = stock)

                db.certificateEntityQueries.let { query ->
                    query.transaction { items?.forEach { item -> query.insert(item.toEntity()) } }
                }

                offset = offset.plus(10_000)

            } while (items.isNullOrEmpty())

        }

        val marks = scope.async {

        }

        articuls.await()
        certificates.await()
        marks.await()
        */

        val articul = ArticulEntity(
            id = 0,
            name = "Ice Edge Mini FS V2.0",
            producer = "DeepCool",
            7
        )

        val certificate = CertificateEntity(
            id = 0,
            name = "",
            articul = 0
        )

        val barcode = BarcodeEntity(
            barcode = "6933412725527",
            certificate = 0
        )

        db.transaction {
            db.articulEntityQueries.insert(articul)
            db.certificateEntityQueries.insert(certificate)
            db.barcodeEntityQueries.insert(barcode)
        }
    }
}