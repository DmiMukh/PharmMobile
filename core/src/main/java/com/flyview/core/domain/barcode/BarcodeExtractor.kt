package com.flyview.core.domain.barcode

import com.flyview.core.data.barcode.Barcode

interface BarcodeExtractor {
    fun getShortCode(code: String, codeType: Barcode): String
}