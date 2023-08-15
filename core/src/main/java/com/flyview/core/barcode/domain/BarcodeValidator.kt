package com.flyview.core.barcode.domain

interface BarcodeValidator {
    fun isValid(data: String): Boolean
}