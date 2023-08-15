package com.flyview.core.data.barcode

import com.flyview.core.data.barcode.code.DataMatrix85
import com.flyview.core.data.barcode.code.EAN13
import com.flyview.core.data.barcode.code.UnknownBarcode
import com.flyview.core.data.barcode.validator.DataMatrix85Validator
import com.flyview.core.data.barcode.validator.EAN13Validator
import com.flyview.core.domain.barcode.BarcodeBinder
import com.flyview.core.domain.barcode.BarcodeValidator

class InvBarcodeBinder : BarcodeBinder {

    val ean13Validator: BarcodeValidator = EAN13Validator()
    val dataMatrix85Validator: BarcodeValidator = DataMatrix85Validator()
    override fun createBarcode(data: String): Barcode {
        when (data.length) {
            13 -> { if (ean13Validator.isValid(data)) return EAN13(code = data) }
            85 -> { if (dataMatrix85Validator.isValid(data)) return DataMatrix85(code = data) }
        }

        return UnknownBarcode()
    }
}