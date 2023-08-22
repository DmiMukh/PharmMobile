package com.flyview.pharmmobile.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.core.message.ui.MessageComponent
import com.flyview.inventory_feature.ui.InventoryRootComponent
import com.flyview.pharmmobile.home.HomeComponent
import com.flyview.pharmmobile.settings.SettingsComponent
import com.flyview.pharmmobile.splash.SplashComponent
import com.flyview.pharmmobile.usb_device.UsbListComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    val messageComponent: MessageComponent
    
    sealed interface Child {

        class Home(val component: HomeComponent) : Child
        class InventoryRoot(val component: InventoryRootComponent) : Child
        class Settings(val component: SettingsComponent) : Child
        class Splash(val component: SplashComponent) : Child
        class UsbList(val component: UsbListComponent) : Child
    }
}