package com.flyview.core.data.barcode

import com.flyview.core.data.barcode.validator.DataMatrix85Validator
import com.flyview.core.data.barcode.validator.EAN13Validator
import com.flyview.core.domain.barcode.BarcodeBinder
import com.flyview.core.domain.barcode.BarcodeValidator

class InvBarcodeBinder : BarcodeBinder {

    val ean13Validator: BarcodeValidator = EAN13Validator()
    val dataMatrix85Validator: BarcodeValidator = DataMatrix85Validator()
    override fun CreateBarcode(data: String) = when (data.length) {
        13 -> {
            if (ean13Validator.isValid(data)) Barcode.EAN13
            else Barcode.Unknown
        }

        85 -> {
            if (dataMatrix85Validator.isValid(data)) Barcode.DataMatrix85
            else Barcode.Unknown
        }

        else -> Barcode.Unknown
    }
}