package com.flyview.core.barcode_reader.data.extractor

import com.flyview.core.barcode_reader.domain.BarcodeExtractor

class EAN13Extractor : BarcodeExtractor {
    override fun getEAN(code: String) = code
    override fun getShortCode(code: String) = code
}