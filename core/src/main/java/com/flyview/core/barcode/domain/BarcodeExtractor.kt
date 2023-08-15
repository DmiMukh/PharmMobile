package com.flyview.core.barcode.domain

interface BarcodeExtractor {
    fun getEAN(code: String): String
    fun getShortCode(code: String): String
}