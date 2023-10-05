package com.flyview.pharmmobile.domain.barcode_reader

import android.graphics.SurfaceTexture
import com.zebra.adc.decoder.BarCodeReader

interface EmbeddedBarcodeReader {
    fun onDecodeComplete(
        symbology: Int,
        length: Int,
        data: ByteArray?,
        reader: BarCodeReader?
    )

    fun onPauseBefore()
    fun onPauseAfter()
    fun onResume(
        onFinish: () -> Unit, cb: BarCodeReader.DecodeCallback,
        er: BarCodeReader.ErrorCallback,
        st: SurfaceTexture.OnFrameAvailableListener
    )
}