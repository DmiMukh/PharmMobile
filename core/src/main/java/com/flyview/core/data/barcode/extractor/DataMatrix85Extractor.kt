package com.flyview.core.data.barcode.extractor

import com.flyview.core.domain.barcode.BarcodeExtractor

class DataMatrix85Extractor : BarcodeExtractor {
    override fun getEAN(code: String) = code.substring(3, 16)
    override fun getShortCode(code: String) = code.substring(2, 16).plus(code.substring(18, 31))
}