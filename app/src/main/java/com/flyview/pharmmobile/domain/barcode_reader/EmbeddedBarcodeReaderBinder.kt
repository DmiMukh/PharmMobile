package com.flyview.pharmmobile.domain.barcode_reader

interface EmbeddedBarcodeReaderBinder {
    fun createBarcodeReader(): EmbeddedBarcodeReader
}