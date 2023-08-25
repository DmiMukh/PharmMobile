package com.flyview.inventory_feature.domain.model

import com.flyview.inventory_feature.domain.request.MarkRequest

data class Mark(
    val certificate: Int,
    val code: String,
    val fullCode: String?
)

fun Mark.toRequest() = MarkRequest(
    certificate = this.certificate,
    code = this.code
)