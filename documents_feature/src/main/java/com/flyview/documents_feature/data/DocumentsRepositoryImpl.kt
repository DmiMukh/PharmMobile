package com.flyview.documents_feature.data

import androidx.paging.PagingData
import com.flyview.core.message.data.MessageService
import com.flyview.documents_feature.domain.DocumentsRepository
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.model.MarkCode
import com.flyview.documents_feature.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

class DocumentsRepositoryImpl(
    private val api: DocumentsApi,
    private val messageService: MessageService
): DocumentsRepository {
    override suspend fun getDocuments(date: LocalDate): List<Document> {
        return listOf(
            Document(
                id = 0,
                number = "123456789",
                recieverName = "Торговый зал",
                senderName = "Агроресурсы",
                totalSum = 100500.0,
                typeName = "Н/П"
            ),
            Document(
                id = 0,
                number = "123456789",
                recieverName = "Торговый зал",
                senderName = "Агроресурсы",
                totalSum = 100500.0,
                typeName = "Н/П"
            ),
            Document(
                id = 0,
                number = "123456789",
                recieverName = "Торговый зал",
                senderName = "Агроресурсы",
                totalSum = 100500.0,
                typeName = "Н/П"
            )
        )
    }

    override fun getMarksPager(documentId: Long): Flow<PagingData<MarkCode>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProducts(document: Long): List<Product> {

        /*
        api.getArticuls(document)
        api.getBarcodes(document)
        api.getCertificates(document)
        */
        return emptyList()
    }

    override fun getProductsPager(documentId: Long): Flow<PagingData<Product>> {
        TODO("Not yet implemented")
    }
}