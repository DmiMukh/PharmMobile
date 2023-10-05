package com.flyview.pharmmobile.data.barcode_reader

import android.graphics.SurfaceTexture
import com.flyview.pharmmobile.domain.barcode_reader.EmbeddedBarcodeReader
import com.zebra.adc.decoder.BarCodeReader

class BarcodeReaderEmpty : EmbeddedBarcodeReader {
    override fun onDecodeComplete(
        symbology: Int,
        length: Int,
        data: ByteArray?,
        reader: BarCodeReader?
    ) = Unit

    override fun onPauseBefore() = Unit

    override fun onPauseAfter() = Unit

    override fun onResume(
        onFinish: () -> Unit,
        cb: BarCodeReader.DecodeCallback,
        er: BarCodeReader.ErrorCallback,
        st: SurfaceTexture.OnFrameAvailableListener
    ) = Unit
}