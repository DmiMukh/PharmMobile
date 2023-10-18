package com.flyview.core.barcode.data.extractor

import com.flyview.core.barcode.domain.BarcodeExtractor

class Code128Extractor(private val code: String): BarcodeExtractor {
    override fun getEAN() = ""
    override fun getShortCode() = this.code.substring(3, 20)
}