package com.flyview.core.data.barcode

sealed class Barcode {
    object DataMatrix85 : Barcode()
    object EAN13 : Barcode()
    object Unknown : Barcode()
}
