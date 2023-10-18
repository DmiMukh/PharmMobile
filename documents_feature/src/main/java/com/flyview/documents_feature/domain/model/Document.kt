package com.flyview.documents_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val id: Long,
    val number: String,
    val recieverName: String,
    val senderName: String,
    val totalSum: Double,
    val typeName: String
): Parcelable {
    constructor() : this(
        id = 0,
        number = "fake_number",
        recieverName = "reciever",
        senderName = "sender",
        totalSum = 100500.0,
        typeName = "Н/П"
    )
}