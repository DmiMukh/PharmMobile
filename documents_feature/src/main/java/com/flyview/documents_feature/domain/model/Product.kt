package com.flyview.documents_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val articul: Articul,
    val certificate: Certificate
) : Parcelable {
    constructor() : this(
        articul = Articul(),
        certificate = Certificate()
    )
}