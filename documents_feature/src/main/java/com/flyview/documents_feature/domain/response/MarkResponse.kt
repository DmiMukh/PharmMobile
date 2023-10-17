package com.flyview.documents_feature.domain.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarkResponse(
    val code: String,
    @SerialName("full_code")
    val fullCode: String,
    val certificate: Int
)
