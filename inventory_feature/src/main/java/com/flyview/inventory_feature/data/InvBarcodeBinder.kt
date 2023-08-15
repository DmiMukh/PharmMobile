package com.flyview.inventory_feature.data

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.code.DataMatrix85
import com.flyview.core.barcode.data.code.EAN13
import com.flyview.core.barcode.data.code.UnknownBarcode
import com.flyview.core.barcode.data.validator.DataMatrix85Validator
import com.flyview.core.barcode.data.validator.EAN13Validator
import com.flyview.core.barcode.domain.BarcodeBinder
import com.flyview.core.barcode.domain.BarcodeValidator

class InvBarcodeBinder : BarcodeBinder {

    val ean13Validator: BarcodeValidator = EAN13Validator()
    val dataMatrix85Validator: BarcodeValidator = DataMatrix85Validator()
    override fun createBarcode(data: String): Barcode {
        when (data.length) {
            13 -> { if (ean13Validator.isValid(data)) return EAN13(code = data)
            }
            85 -> { if (dataMatrix85Validator.isValid(data)) return DataMatrix85(code = data)
            }
        }

        return UnknownBarcode()
    }
}