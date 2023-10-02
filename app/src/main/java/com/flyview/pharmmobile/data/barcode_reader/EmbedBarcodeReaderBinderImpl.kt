package com.flyview.pharmmobile.data.barcode_reader

import android.os.Build
import com.flyview.pharmmobile.domain.barcode_reader.EmbedBarcodeReader
import com.flyview.pharmmobile.domain.barcode_reader.EmbedBarcodeReaderBinder

class EmbedBarcodeReaderBinderImpl: EmbedBarcodeReaderBinder {

    /*
    private val MANUFACTURER_NAME = "ATOL"
    private val DEVICE = "T200"
    */
    override fun createBarcodeReader(): EmbedBarcodeReader {

        val device = Build.DEVICE.uppercase()
        val manufacturer = Build.MANUFACTURER.uppercase()

        // ATOL SMART.PRO
        if (device == "ATOL" && manufacturer == "T200") {

        }
        TODO("Not yet implemented")
    }
}