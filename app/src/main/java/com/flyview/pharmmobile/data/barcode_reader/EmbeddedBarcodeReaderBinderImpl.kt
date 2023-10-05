package com.flyview.pharmmobile.data.barcode_reader

import android.content.Context
import android.os.Build
import com.flyview.core.media.AudioPlayer
import com.flyview.pharmmobile.domain.barcode_reader.EmbeddedBarcodeReader
import com.flyview.pharmmobile.domain.barcode_reader.EmbeddedBarcodeReaderBinder

class EmbeddedBarcodeReaderBinderImpl(
    private val context: Context,
    private val audioPlayer: AudioPlayer
) : EmbeddedBarcodeReaderBinder {
    override fun createBarcodeReader(): EmbeddedBarcodeReader {

        val device = Build.DEVICE.uppercase()
        val manufacturer = Build.MANUFACTURER.uppercase()

        // ATOL SMART.PRO
        if (device == "ATOL" && manufacturer == "T200") {
            return BarcodeReaderT200(
                context = context,
                audioPlayer = audioPlayer
            )
        }

        return BarcodeReaderEmpty()
    }
}