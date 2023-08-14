package com.flyview.inventory_feature.domain

import com.flyview.inventoryfeature.SelectByBarcode
import com.flyview.inventoryfeature.SelectBySgtin

data class Certificate(
    val id: Long,
    val name: String
) {
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