package com.flyview.mark_feature.domain.model

data class Document(
    val id: Int,
    val number: String,
    val recieverName: String,
    val senderName: String,
    val totalSum: Double
) {
    constructor() : this(
        id = 0,
        number = "fake_number",
        recieverName = "reciever",
        senderName = "sender",
        totalSum = 100500.0
    )
}