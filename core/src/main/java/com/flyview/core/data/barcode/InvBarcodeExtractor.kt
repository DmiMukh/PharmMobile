package com.flyview.core.data.barcode

import com.flyview.core.domain.barcode.BarcodeExtractor

class InvBarcodeExtractor : BarcodeExtractor {



    override fun getShortCode(code: String, codeType: Barcode) =
        when (codeType) {
            Barcode.DataMatrix85 -> TODO()
            Barcode.EAN13 -> code
            Barcode.Unknown -> ""
        }
}