package com.flyview.core.data.barcode.code

import com.flyview.core.data.barcode.Barcode
import com.flyview.core.domain.barcode.BarcodeExtractor

class UnknownBarcode: Barcode {
    override val code = ""
    override val extractor: BarcodeExtractor
        get() = TODO("Not yet implemented")
}