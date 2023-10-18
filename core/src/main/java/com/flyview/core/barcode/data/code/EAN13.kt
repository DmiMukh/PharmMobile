package com.flyview.core.barcode.data.code

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.extractor.EAN13Extractor
import com.flyview.core.barcode.domain.BarcodeExtractor

class EAN13 constructor(override val code: String) : Barcode {

    override val extractor: BarcodeExtractor = EAN13Extractor(this.code)
}