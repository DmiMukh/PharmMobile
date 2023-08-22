package com.flyview.inventory_feature.domain.response

import com.flyview.inventoryfeature.MarkEntity

data class MarkResponse(
    val certificate: Long,
    val code: String,
    val fullCode: String
)

fun MarkResponse.toEntity() = MarkEntity(
    sgtin = this.code,
    certificate = this.certificate
)