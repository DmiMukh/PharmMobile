package com.flyview.inventory_feature.domain

import com.flyview.inventoryfeature.SelectByBarcode
import com.flyview.inventoryfeature.SelectBySgtin

data class Articul(
    val id: Long,
    val name: String,
    val producer: String,
    val divisibility: Int
) {
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