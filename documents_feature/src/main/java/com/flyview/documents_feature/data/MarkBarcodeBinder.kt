package com.flyview.documents_feature.data

import com.flyview.core.barcode.data.Barcode
import com.flyview.core.barcode.data.code.Code128
import com.flyview.core.barcode.data.code.DataMatrix85
import com.flyview.core.barcode.data.code.UnknownBarcode
import com.flyview.core.barcode.data.validator.Code128Validator
import com.flyview.core.barcode.data.validator.DataMatrix85Validator
import com.flyview.core.barcode.domain.BarcodeBinder
import com.flyview.core.barcode.domain.BarcodeValidator

class MarkBarcodeBinder : BarcodeBinder {

    private val code128Validator: BarcodeValidator = Code128Validator()
    private val dataMatrix85Validator: BarcodeValidator = DataMatrix85Validator()
    override fun createBarcode(data: String): Barcode {
        when (data.length) {
            20 -> {
                if (code128Validator.isValid(data)) return Code128(code = data)
            }

            85 -> {
                if (dataMatrix85Validator.isValid(data)) return DataMatrix85(code = data)
            }
        }

        return UnknownBarcode()
    }
}