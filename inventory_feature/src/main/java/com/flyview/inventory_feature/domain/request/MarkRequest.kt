package com.flyview.inventory_feature.domain.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("mark")
data class MarkRequest(
    val certificate: Int,
    val code: String
)
