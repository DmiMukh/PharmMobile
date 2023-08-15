package com.flyview.core.barcode_reader.domain

import com.flyview.core.barcode_reader.data.Barcode

interface BarcodeBinder {
    fun createBarcode(data: String): Barcode
}