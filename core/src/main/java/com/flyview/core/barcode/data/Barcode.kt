package com.flyview.core.barcode.data

import com.flyview.core.barcode.domain.BarcodeExtractor

interface Barcode {

    val code: String
    val extractor: BarcodeExtractor
}