package com.flyview.core.barcode.data.extractor

import com.flyview.core.barcode.domain.BarcodeExtractor

class EAN13Extractor : BarcodeExtractor {
    override fun getEAN(code: String) = code
    override fun getShortCode(code: String) = code
}