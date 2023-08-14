package com.flyview.inventory_feature.domain

import com.flyview.inventoryfeature.GoodEntity
import com.flyview.inventoryfeature.SelectByBarcode
import com.flyview.inventoryfeature.SelectBySgtin

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

fun Product.isValid(): Boolean = (this.articul.id > 0)

fun SelectByBarcode.toDomain(sgtin: String) = Product(
    articul = this.toDomainArticul(),
    certificate = this.toDomainCertificate(),
    sgtin = sgtin,
    quantity = this.quantity
)

fun SelectBySgtin.toDomain(sgtin: String) = Product(
    articul = this.toDomainArticul(),
    certificate = this.toDomainCertificate(),
    sgtin = sgtin,
    quantity = this.quantity
)