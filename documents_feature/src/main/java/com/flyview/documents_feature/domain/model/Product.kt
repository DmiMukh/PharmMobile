package com.flyview.documents_feature.domain.model

data class Product(
    val articul: Articul,
    val certificate: Certificate
) {
    constructor() : this(
        articul = Articul(),
        certificate = Certificate()
    )
}
