package com.flyview.inventory_feature.domain.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("document")
data class DocumentRequest(
    val expectedDate: String,
    val firm: Int,
    val heap: String,
    val id: Int,
    val opDate: String,
    val partner: Int,
    val stock: Int,
    @SerialName("goods") val products: List<GoodRequest>? = null,
    @SerialName("marks") val marks: List<MarkRequest>? = null
)
