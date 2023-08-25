package com.flyview.inventory_feature.domain.response

import com.flyview.inventoryfeature.BarcodeEntity
import com.flyview.inventoryfeature.CertificateEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("certificate")
data class CertificateResponse(
    val id: Long,
    val name: String,
    val articul: Long,
    val barcode: String
)

fun CertificateResponse.toEntity() = CertificateEntity(
    id = this.id,
    name = this.name,
    articul = this.articul
)

fun CertificateResponse.toBarcodeEntity() = BarcodeEntity(
    barcode = this.barcode,
    certificate = this.id
)