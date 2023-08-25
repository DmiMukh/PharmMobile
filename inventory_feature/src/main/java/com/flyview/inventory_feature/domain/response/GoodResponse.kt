package com.flyview.inventory_feature.domain.response

import kotlinx.serialization.Serializable

@Serializable
data class GoodResponse(
    val certificate: Long,
    val quantity: Double
)
