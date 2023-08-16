package com.flyview.inventory_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.inventoryfeature.DocumentEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val id: Long,
    val number: String
) : Parcelable {
    constructor() : this(
        id = -1,
        number = ""
    )
}

fun DocumentEntity.toDomain() = Document(
    id = id,
    number = number
)