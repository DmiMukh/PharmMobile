package com.flyview.inventory_feature.domain.request

import kotlinx.serialization.Serializable

@Serializable
data class GoodRequest(
    val articul: Int,
    val certificate: Int,
    val quantity: Double
)
