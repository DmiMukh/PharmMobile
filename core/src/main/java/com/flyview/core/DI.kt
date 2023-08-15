package com.flyview.core

import android.content.Context
import android.hardware.usb.UsbManager
import com.arkivanov.decompose.ComponentContext
import com.flyview.core.data.RealMediaPlayer
import com.flyview.core.data.barcode.UsbBarcodeReader
import com.flyview.core.domain.AudioPlayer
import com.flyview.core.domain.barcode.BarcodeReader
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.data.MessageServiceImpl
import com.flyview.core.message.ui.MessageComponent
import com.flyview.core.message.ui.RealMessageComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.EmptyCoroutineContext
import org.koin.core.component.get

val coreModule = module {
    single<CoroutineScope> { provideAppScope() }
    single<UsbManager> { provideUsbManager(context = get()) }
    single<MessageService> { MessageServiceImpl() }
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

fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(
        componentContext = componentContext,
        messageService = get()
    )
}
