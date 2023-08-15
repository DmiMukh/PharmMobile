package com.flyview.core.barcode_reader.data.extractor

import com.flyview.core.barcode_reader.domain.BarcodeExtractor

class DataMatrix85Extractor : BarcodeExtractor {
    override fun getEAN(code: String) = code.substring(3, 16)
    override fun getShortCode(code: String) = code.substring(2, 16).plus(code.substring(18, 31))
}