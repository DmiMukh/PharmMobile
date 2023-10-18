package com.flyview.documents_feature.domain.model

data class MarkCode(
    val code: String,
    val saved: Boolean
) {
    constructor() : this(
        code = "000000000000000000000000000",
        saved = false
    )
}
