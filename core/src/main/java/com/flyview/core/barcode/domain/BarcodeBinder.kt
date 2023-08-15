package com.flyview.core.barcode.domain

import com.flyview.core.barcode.data.Barcode

interface BarcodeBinder {
    fun createBarcode(data: String): Barcode
}