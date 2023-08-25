package com.flyview.inventory_feature.domain.response

import com.flyview.inventoryfeature.ArticulEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("articul")
data class ArticulResponse(
    val id: Long,
    val name: String,
    val producer: String,
    val divisibility: Long
)

fun ArticulResponse.toEntity() = ArticulEntity(
    id = this.id,
    name = this.name,
    producer = this.producer,
    divisibility = this.divisibility
)