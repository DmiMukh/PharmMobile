package com.flyview.documents_feature.domain.model

data class Certificate(
    val id: Int,
    val name: String,
    val marked: Boolean
) {
    constructor() : this(
        id = -1,
        name = "001",
        marked = false
    )
}
