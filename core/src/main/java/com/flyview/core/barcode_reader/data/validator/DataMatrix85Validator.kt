package com.flyview.core.barcode_reader.data.validator

import com.flyview.core.barcode_reader.domain.BarcodeValidator
import com.flyview.core.utils.GS1

class DataMatrix85Validator: BarcodeValidator {

    val regex = Regex(
        "01".plus("[0-9]{14}").plus("21").plus("\\S{13}")
            .plus(GS1).plus("91").plus("\\S{4}").plus(GS1).plus("92")
            .plus("\\S{44}")
    )
    override fun isValid(data: String): Boolean = regex.matches(data)
}