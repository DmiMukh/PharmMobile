package com.flyview.core.barcode_reader.data.code

import com.flyview.core.barcode_reader.data.Barcode
import com.flyview.core.barcode_reader.data.extractor.EAN13Extractor
import com.flyview.core.barcode_reader.domain.BarcodeExtractor

class EAN13 constructor(code: String) : Barcode {

    override val code = code
    override val extractor: BarcodeExtractor = EAN13Extractor()
}