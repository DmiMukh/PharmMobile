package com.flyview.inventory_feature.domain

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val id: Long,
    val number: String
) : Parcelable {
    constructor() : this(
        id = -1,
        number = ""
    )
}