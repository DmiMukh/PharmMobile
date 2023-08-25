package com.flyview.core.barcode.data.code

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.extractor.UnknowBarcodeExtractor

class UnknownBarcode: Barcode {
    override val code = ""
    override val extractor = UnknowBarcodeExtractor()
}