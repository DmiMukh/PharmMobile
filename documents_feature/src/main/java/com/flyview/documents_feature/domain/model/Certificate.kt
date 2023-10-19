package com.flyview.documents_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Certificate(
    val id: Int,
    val name: String,
    val marked: Boolean
) : Parcelable {
    constructor() : this(
        id = -1,
        name = "001",
        marked = false
    )
}
