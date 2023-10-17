package com.flyview.documents_feature.domain

import com.flyview.documents_feature.domain.model.Document
import kotlinx.datetime.LocalDate

interface DocumentsRepository {

    fun getDocuments(date: LocalDate): List<Document>
}