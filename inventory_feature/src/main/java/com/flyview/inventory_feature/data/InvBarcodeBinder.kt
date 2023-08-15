package com.flyview.inventory_feature.data

import com.flyview.core.barcode_reader.data.Barcode
import com.flyview.core.barcode_reader.data.code.DataMatrix85
import com.flyview.core.barcode_reader.data.code.EAN13
import com.flyview.core.barcode_reader.data.code.UnknownBarcode
import com.flyview.core.barcode_reader.data.validator.DataMatrix85Validator
import com.flyview.core.barcode_reader.data.validator.EAN13Validator
import com.flyview.core.barcode_reader.domain.BarcodeBinder
import com.flyview.core.barcode_reader.domain.BarcodeValidator

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