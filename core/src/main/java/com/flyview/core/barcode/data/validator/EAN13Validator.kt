package com.flyview.core.barcode.data.validator

import com.flyview.core.barcode.domain.BarcodeValidator

class EAN13Validator: BarcodeValidator {

    val regex = Regex("[0-9]{13}")
    override fun isValid(data: String): Boolean = regex.matches(data)
}