package com.flyview.core.data.barcode.code

import com.flyview.core.data.barcode.Barcode
import com.flyview.core.data.barcode.extractor.EAN13Extractor
import com.flyview.core.data.barcode.validator.EAN13Validator
import com.flyview.core.domain.barcode.BarcodeExtractor
import com.flyview.core.domain.barcode.BarcodeValidator

class EAN13 constructor(code: String) : Barcode {

    override val code = code
    override val extractor: BarcodeExtractor = EAN13Extractor()
}