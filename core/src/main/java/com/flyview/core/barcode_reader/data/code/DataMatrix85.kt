package com.flyview.core.barcode_reader.data.code

import com.flyview.core.barcode_reader.data.Barcode
import com.flyview.core.barcode_reader.data.extractor.DataMatrix85Extractor
import com.flyview.core.barcode_reader.domain.BarcodeExtractor

class DataMatrix85 constructor(code: String): Barcode {

    override val code = code
    override val extractor: BarcodeExtractor = DataMatrix85Extractor()
}