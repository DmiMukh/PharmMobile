package com.flyview.core.data.barcode.extractor

import com.flyview.core.domain.barcode.BarcodeExtractor

class EAN13Extractor : BarcodeExtractor {
    override fun getEAN(code: String) = code
    override fun getShortCode(code: String) = code
}