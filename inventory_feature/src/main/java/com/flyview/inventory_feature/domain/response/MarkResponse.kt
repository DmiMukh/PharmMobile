package com.flyview.inventory_feature.domain.response

import com.flyview.inventoryfeature.MarkEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("mark")
data class MarkResponse(
    val certificate: Long,
    val code: String
)

fun MarkResponse.toEntity() = MarkEntity(
    sgtin = this.code,
    certificate = this.certificate
)