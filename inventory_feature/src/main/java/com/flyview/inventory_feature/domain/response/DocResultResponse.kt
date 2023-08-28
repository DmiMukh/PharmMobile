package com.flyview.inventory_feature.domain.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("doc_result")
data class DocResultResponse(
    val id: Int,
    val number: String,
    @SerialName("error_code") val errorCode: String,
    @SerialName("error_params") val errorParams: String
)
