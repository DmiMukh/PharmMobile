package com.flyview.core.barcode.data.code

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.extractor.DataMatrix85Extractor
import com.flyview.core.barcode.domain.BarcodeExtractor

class DataMatrix85 constructor(code: String): Barcode {

    override val code = code
    override val extractor: BarcodeExtractor = DataMatrix85Extractor()
}