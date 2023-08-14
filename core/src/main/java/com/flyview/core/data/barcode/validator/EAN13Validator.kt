package com.flyview.core.data.barcode.validator

import com.flyview.core.domain.barcode.BarcodeValidator

class EAN13Validator: BarcodeValidator {

    val regex = Regex("[0-9]{13}")
    override fun isValid(data: String): Boolean = regex.matches(data)
}