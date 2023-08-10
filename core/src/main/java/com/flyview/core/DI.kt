package com.flyview.core

import android.content.Context
import android.hardware.usb.UsbManager
import com.flyview.core.data.RealMediaPlayer
import com.flyview.core.data.UsbBarcodeReader
import com.flyview.core.domain.AudioPlayer
import com.flyview.core.domain.BarcodeReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.EmptyCoroutineContext

val coreModule = module {
    single<CoroutineScope> { provideAppScope() }
    single<UsbManager> { provideUsbManager(context = get()) }
    single<AudioPlayer> { RealMediaPlayer(context = get()) }
    single<BarcodeReader> {
        UsbBarcodeReader(
            context = get(),
            appScope = get(),
            usbManager = get(),
            audioPlayer = get()
        )
    }
}

fun provideAppScope(): CoroutineScope {
    return CoroutineScope(EmptyCoroutineContext + Dispatchers.Default)
}

fun provideUsbManager(context: Context): UsbManager {
    return context.getSystemService(Context.USB_SERVICE) as UsbManager
}