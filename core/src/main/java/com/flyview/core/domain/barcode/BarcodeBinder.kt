package com.flyview.core.domain.barcode

import com.flyview.core.data.barcode.Barcode

interface BarcodeBinder {
    fun createBarcode(data: String): Barcode
}