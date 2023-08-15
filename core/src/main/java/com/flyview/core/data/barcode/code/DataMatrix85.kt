package com.flyview.core.data.barcode.code

import com.flyview.core.data.barcode.Barcode
import com.flyview.core.data.barcode.extractor.DataMatrix85Extractor
import com.flyview.core.data.barcode.validator.DataMatrix85Validator
import com.flyview.core.domain.barcode.BarcodeExtractor
import com.flyview.core.domain.barcode.BarcodeValidator

class DataMatrix85 constructor(code: String): Barcode {

    override val code = code
    override val extractor: BarcodeExtractor = DataMatrix85Extractor()
}