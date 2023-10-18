package com.flyview.core.barcode.data.code

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.extractor.Code128Extractor
import com.flyview.core.barcode.domain.BarcodeExtractor

class Code128 constructor(override val code: String): Barcode {
    override val extractor: BarcodeExtractor = Code128Extractor(this.code)
}