package com.flyview.core.domain.barcode

interface BarcodeValidator {
    fun isValid(data: String): Boolean
}