package com.flyview.inventory_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.inventoryfeature.SelectByBarcode
import com.flyview.inventoryfeature.SelectBySgtin
import com.flyview.inventoryfeature.SelectProductsByDocument
import kotlinx.parcelize.Parcelize

@Parcelize
data class Articul(
    val id: Long,
    val name: String,
    val producer: String,
    val divisibility: Int
) : Parcelable {
    constructor() : this(
        id = 0,
        name = "",
        producer = "",
        divisibility = 1
    )
}

fun SelectByBarcode.toDomainArticul() = Articul(
    id = this.articulId,
    name = this.articulName,
    producer = this.producer,
    divisibility = this.divisibility.toInt()
)

fun SelectBySgtin.toDomainArticul() = Articul(
    id = this.articulId,
    name = this.articulName,
    producer = this.producer,
    divisibility = this.divisibility.toInt()
)

fun SelectProductsByDocument.toDomainArtucul() = Articul(
    id = this.articulId,
    name = this.articulName,
    producer = this.producer,
    divisibility = this.divisibility.toInt()
)