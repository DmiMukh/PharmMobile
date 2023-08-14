package com.flyview.inventory_feature.domain

import com.flyview.inventoryfeature.GoodEntity

data class Product(
    val articul: Articul,
    val certificate: Certificate,
    val sgtin: String,
    val quantity: Double
) {
    constructor() : this(
        articul = Articul(),
        certificate = Certificate(),
        sgtin = "",
        quantity = 0.0
    )
}

fun Product.toData(document: Long) = GoodEntity(
    certificate = this.certificate.id,
    sgtin = this.sgtin,
    document = document,
    quantity = this.quantity
)

