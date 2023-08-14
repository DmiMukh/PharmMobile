package com.flyview.inventory_feature.domain

data class Articul(
    val id: Long,
    val name: String,
    val producer: String,
    val divisibility: Int
) {
    constructor() : this(
        id = 0,
        name = "",
        producer = "",
        divisibility = 1
    )
}
