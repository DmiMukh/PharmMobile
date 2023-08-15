package com.flyview.core.data.barcode

import com.flyview.core.domain.barcode.BarcodeExtractor
import com.flyview.core.domain.barcode.BarcodeValidator

interface Barcode {

    val code: String
    val extractor: BarcodeExtractor

    fun getEAN() = extractor.getEAN(this.code)
    fun getShortCode() = extractor.getShortCode(this.code)
}