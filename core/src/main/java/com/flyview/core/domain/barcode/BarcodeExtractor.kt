package com.flyview.core.domain.barcode

import com.flyview.core.data.barcode.Barcode

interface BarcodeExtractor {
    fun getEAN(code: String): String
    fun getShortCode(code: String): String
}