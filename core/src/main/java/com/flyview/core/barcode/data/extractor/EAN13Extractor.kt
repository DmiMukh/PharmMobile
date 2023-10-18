package com.flyview.core.barcode.data.extractor

import com.flyview.core.barcode.domain.BarcodeExtractor

class EAN13Extractor(private val code: String) : BarcodeExtractor {
    override fun getEAN() = this.code
    override fun getShortCode() = this.code
}