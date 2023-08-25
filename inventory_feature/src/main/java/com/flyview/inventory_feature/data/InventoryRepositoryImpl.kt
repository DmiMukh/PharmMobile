package com.flyview.inventory_feature.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import app.cash.sqldelight.paging3.QueryPagingSource
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.storage.SettingsStorage
import com.flyview.core.utils.getCurrentLocalDateTime
import com.flyview.inventory_feature.domain.AGENT
import com.flyview.inventory_feature.domain.FIRM
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.STOCK
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.model.Product
import com.flyview.inventory_feature.domain.model.toData
import com.flyview.inventory_feature.domain.model.toDomain
import com.flyview.inventory_feature.domain.model.toGoodRequest
import com.flyview.inventory_feature.domain.model.toMarkRequest
import com.flyview.inventory_feature.domain.request.DocumentRequest
import com.flyview.inventory_feature.domain.response.ArticulResponse
import com.flyview.inventory_feature.domain.response.CertificateResponse
import com.flyview.inventory_feature.domain.response.MarkResponse
import com.flyview.inventory_feature.domain.response.toBarcodeEntity
import com.flyview.inventory_feature.domain.response.toEntity
import com.flyview.pharmmobile.inventory_feature.InventoryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import java.util.UUID

class InventoryRepositoryImpl(
    private val db: InventoryDatabase,
    private val api: InventoryApi,
    private val storage: SettingsStorage,
    private val messageService: MessageService
) : InventoryRepository {
    override suspend fun clearData() {
        db.transaction {
            db.markEntityQueries.deleteAll()
            db.goodEntityQueries.deleteAll()
            db.documentEntityQueries.deleteAll()
            db.barcodeEntityQueries.deleteAll()
            db.certificateEntityQueries.deleteAll()
            db.articulEntityQueries.deleteAll()
        }

        delay(500)
        messageService.showMessage(Message("Данные удалены!"))
    }

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

    override suspend fun getProductsByDocument(documentId: Long) =
        db.goodEntityQueries.selectProductsByDocument(
            document = documentId,
            limit = 200,
            offset = 0
        ).executeAsList().map { it.toDomain() }

    override fun getDocuments() = db.documentEntityQueries.documents(
        limit = 1_000,
        offset = 0
    ).executeAsList().map { it.toDomain() }


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

    override suspend fun sendDocument(
        document: Document,
        products: List<Product>
    ): Int {
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
            marks = products.map { it.toMarkRequest() }
        )

        try {
            return api.putDocument(data).toInt()
        } catch (ex: Exception) {
            messageService.showMessage(Message(ex.localizedMessage.orEmpty()))
            return -1
        }
    }

    override suspend fun updateDocument(document: Document, infSysId: Int) {
        //TODO("Not yet implemented")
    }

    override suspend fun uploadArticuls(): Boolean {
        val stock = storage.getInt(STOCK)
        var offset = 0L
        var articuls: List<ArticulResponse>?

        do {
            try {
                articuls = api.getArticuls(limit = 10_000, offset = offset)
            } catch (ex: Exception) {
                messageService.showMessage(Message(text = ex.localizedMessage.orEmpty()))
                return false
            }

            db.articulEntityQueries.let { query ->
                query.transaction { articuls.forEach { item -> query.insert(item.toEntity()) } }
            }


            offset = offset.plus(10_000)
        } while (articuls.isNullOrEmpty())

        return true
    }

    override suspend fun uploadCertificates(): Boolean {
        val stock = storage.getInt(STOCK)
        var certificates: List<CertificateResponse>?
        var offset = 0L
        do {
            try {
                certificates = api.getCertificates(limit = 10_000, offset = offset, stock = stock)
            } catch (ex: Exception) {
                messageService.showMessage(Message(text = ex.localizedMessage.orEmpty()))
                return false
            }

            db.certificateEntityQueries.let { query ->
                query.transaction { certificates.forEach { item -> query.insert(item.toEntity()) } }
            }

            db.barcodeEntityQueries.let { query ->
                query.transaction { certificates.forEach { item -> query.insert(item.toBarcodeEntity()) } }
            }

            offset = offset.plus(10_000)

        } while (certificates.isNullOrEmpty())
        return true
    }

    override suspend fun uploadMarks(): Boolean {
        val stock = storage.getInt(STOCK)
        var marks: List<MarkResponse>?
        var offset = 0L
        do {
            try {
                marks = api.getMarks(limit = 10_000, offset = offset, stock = stock)
            } catch (ex: Exception) {
                messageService.showMessage(Message(text = ex.localizedMessage.orEmpty()))
                return false
            }

            db.markEntityQueries.let { query ->
                query.transaction { marks.forEach { item -> query.insert(item.toEntity()) } }
            }

        } while (marks.isNullOrEmpty())
        return true
    }
}