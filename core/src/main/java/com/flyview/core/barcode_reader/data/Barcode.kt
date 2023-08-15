package com.flyview.core.barcode_reader.data

import com.flyview.core.barcode_reader.domain.BarcodeExtractor

interface Barcode {

    val code: String
    val extractor: BarcodeExtractor

    fun getEAN() = extractor.getEAN(this.code)
    fun getShortCode() = extractor.getShortCode(this.code)
}