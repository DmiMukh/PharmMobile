package com.flyview.core.barcode.data.extractor

import com.flyview.core.barcode.domain.BarcodeExtractor

class UnknowBarcodeExtractor: BarcodeExtractor {
    override fun getEAN(code: String) = ""

    override fun getShortCode(code: String) = ""
}