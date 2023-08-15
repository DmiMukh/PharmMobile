package com.flyview.core.barcode.data.code

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.domain.BarcodeExtractor

class UnknownBarcode: Barcode {
    override val code = ""
    override val extractor: BarcodeExtractor
        get() = TODO("Not yet implemented")
}