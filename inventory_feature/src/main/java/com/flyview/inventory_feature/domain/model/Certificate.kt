package com.flyview.inventory_feature.domain.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.inventoryfeature.SelectByBarcode
import com.flyview.inventoryfeature.SelectBySgtin
import kotlinx.parcelize.Parcelize

@Parcelize
data class Certificate(
    val id: Long,
    val name: String
) : Parcelable {
    constructor() : this(
        id = 0,
        name = ""
    )
}

fun SelectByBarcode.toDomainCertificate() = Certificate(
    id = this.certificateId,
    name = this.certificateName
)

fun SelectBySgtin.toDomainCertificate() = Certificate(
    id = this.certificateId,
    name = this.certificateName
)