package com.flyview.core.barcode_reader.data.code

import com.flyview.core.barcode_reader.data.Barcode
import com.flyview.core.barcode_reader.domain.BarcodeExtractor

class UnknownBarcode: Barcode {
    override val code = ""
    override val extractor: BarcodeExtractor
        get() = TODO("Not yet implemented")
}