package com.flyview.core.barcode_reader.domain

interface BarcodeValidator {
    fun isValid(data: String): Boolean
}