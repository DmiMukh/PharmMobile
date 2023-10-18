package com.flyview.core.barcode.domain

interface BarcodeExtractor {
    fun getEAN(): String
    fun getShortCode(): String
}