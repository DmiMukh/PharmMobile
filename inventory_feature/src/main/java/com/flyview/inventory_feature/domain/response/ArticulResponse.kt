package com.flyview.inventory_feature.domain.response

data class ArticulResponse(
    val id: Long,
    val name: String,
    val producer: String,
    val divisibility: Long
)
