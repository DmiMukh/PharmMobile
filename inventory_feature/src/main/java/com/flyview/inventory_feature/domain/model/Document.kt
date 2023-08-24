package com.flyview.inventory_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.inventoryfeature.DocumentEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val id: Long,
    val number: String,
    val formattedCreationDate: String,
    val sended: Boolean
) : Parcelable {
    constructor() : this(
        id = -1,
        number = "",
        formattedCreationDate = "",
        sended = false
    )
}

fun DocumentEntity.toDomain() = Document(
    id = id,
    number = number,
    formattedCreationDate = this.creation_date,
    sended = this.is_id > 0
)