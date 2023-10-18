package com.flyview.documents_feature.domain.model

data class Articul(
    val id: Int,
    val name: String
) {
    constructor() : this (
        id = -1,
        name = "Тестинол"
    )
}