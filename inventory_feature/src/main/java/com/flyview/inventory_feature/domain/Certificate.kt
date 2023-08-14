package com.flyview.inventory_feature.domain

data class Certificate(
    val id: Long,
    val name: String
) {
    constructor() : this(
        id = 0,
        name = ""
    )
}
