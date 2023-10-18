package com.flyview.core.barcode.data.code

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.extractor.DataMatrix85Extractor
import com.flyview.core.barcode.domain.BarcodeExtractor

class DataMatrix85 constructor(override val code: String): Barcode {

    override val extractor: BarcodeExtractor = DataMatrix85Extractor(this.code)
}