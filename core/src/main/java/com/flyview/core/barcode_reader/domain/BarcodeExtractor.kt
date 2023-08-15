package com.flyview.core.barcode_reader.domain

interface BarcodeExtractor {
    fun getEAN(code: String): String
    fun getShortCode(code: String): String
}