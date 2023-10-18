package com.flyview.core.barcode.data.validator

import com.flyview.core.barcode.domain.BarcodeValidator

class Code128Validator : BarcodeValidator {
    val regex = Regex("[0-9]{20}")
    override fun isValid(data: String): Boolean = regex.matches(data)
}