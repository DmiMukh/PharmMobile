package com.flyview.pharmmobile

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.core.coreModule
import com.flyview.inventory_feature.inventoryModule
import com.flyview.mark_feature.markModule
import com.flyview.pharmmobile.data.barcode_reader.EmbeddedBarcodeReaderBinderImpl
import com.flyview.pharmmobile.domain.barcode_reader.EmbeddedBarcodeReader
import com.flyview.pharmmobile.home.HomeComponent
import com.flyview.pharmmobile.home.RealHomeComponent
import com.flyview.pharmmobile.settings.RealSettingsComponent
import com.flyview.pharmmobile.settings.SettingsComponent
import com.flyview.pharmmobile.splash.RealSplashComponent
import com.flyview.pharmmobile.splash.SplashComponent
import com.flyview.pharmmobile.usb_device.RealUsbListComponent
import com.flyview.pharmmobile.usb_device.UsbListComponent
import org.koin.core.component.get
import org.koin.dsl.module

val allModules = listOf(
    coreModule,
    inventoryModule,
    markModule,
    module {
        single<EmbeddedBarcodeReader> {
            EmbeddedBarcodeReaderBinderImpl(
                context = get(),
                audioPlayer = get()
            ).createBarcodeReader()
        }
    }
)

fun ComponentFactory.createHomeComponent(
    componentContext: ComponentContext,
    onBarcodeReaderClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onDocumentsCLick: () -> Unit,
    onInventoryClick: () -> Unit
): HomeComponent {
    return RealHomeComponent(
        componentContext = componentContext,
        onBarcodeReaderClick = onBarcodeReaderClick,
        onSettingsClick = onSettingsClick,
        onDocumentsCLick = onDocumentsCLick,
        onInventoryClick = onInventoryClick
    )
}

fun ComponentFactory.createSettingsComponent(
    componentContext: ComponentContext,
    onBackClick: () -> Unit
): SettingsComponent {
    return RealSettingsComponent(
        componentContext = componentContext,
        onBack = onBackClick,
        storage = get(),
        messageService = get()
    )
}

fun ComponentFactory.createSplashComponent(
    componentContext: ComponentContext,
    onFinish: () -> Unit
): SplashComponent {
    return RealSplashComponent(
        componentContext = componentContext,
        onFinish = onFinish
    )
}

fun ComponentFactory.createUsbListComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): UsbListComponent {
    return RealUsbListComponent(
        componentContext = componentContext,
        onBack = onBack,
        usbManager = get(),
        messageService = get()
    )
}