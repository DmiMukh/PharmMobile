package com.flyview.documents_feature.data

import com.flyview.core.message.data.MessageService
import com.flyview.documents_feature.domain.DocumentsRepository
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.model.Product
import kotlinx.datetime.LocalDate

class DocumentsRepositoryImpl(
    private val api: DocumentsApi,
    private val messageService: MessageService
): DocumentsRepository {
    override suspend fun getProducts(document: Long): List<Product> {

        /*
        api.getArticuls(document)
        api.getBarcodes(document)
        api.getCertificates(document)
        */
        return emptyList()
    }

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
}