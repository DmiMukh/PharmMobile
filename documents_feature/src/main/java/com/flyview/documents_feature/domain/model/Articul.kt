package com.flyview.documents_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Articul(
    val id: Int,
    val name: String
) : Parcelable {
    constructor() : this (
        id = -1,
        name = "Тестинол"
    )
}