package com.flyview.core.barcode.data.extractor

import com.flyview.core.barcode.domain.BarcodeExtractor

class DataMatrix85Extractor(private val code: String): BarcodeExtractor {
    override fun getEAN() = this.code.substring(3, 16)
    override fun getShortCode() = this.code.substring(2, 16).plus(code.substring(18, 31))
}