package com.flyview.inventory_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.inventoryfeature.GoodEntity
import com.flyview.inventoryfeature.SelectByBarcode
import com.flyview.inventoryfeature.SelectBySgtin
import com.flyview.inventoryfeature.SelectProductsByDocument
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val articul: Articul,
    val certificate: Certificate,
    val sgtin: String,
    val quantity: Double
) : Parcelable {
    constructor() : this(
        articul = Articul(),
        certificate = Certificate(),
        sgtin = "",
        quantity = 0.0
    )
}

fun Product.toData(document: Long, quantity: Double) = GoodEntity(
    certificate = this.certificate.id,
    sgtin = this.sgtin,
    document = document,
    quantity = quantity
)

fun Product.isValid(): Boolean = (this.articul.id > -1)

fun SelectProductsByDocument.toDomain() = Product(
    articul = this.toDomainArtucul(),
    certificate = this.toDomainCertificate(),
    sgtin = this.sgtin,
    quantity = this.quantity
)

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