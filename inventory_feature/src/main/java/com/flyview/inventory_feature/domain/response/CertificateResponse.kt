package com.flyview.inventory_feature.domain.response

import com.flyview.inventoryfeature.CertificateEntity

data class CertificateResponse(
    val id: Long,
    val name: String
)

fun CertificateResponse.toEntity() = CertificateEntity(
    id = this.id,
    name = this.name,
    articul = 0
)