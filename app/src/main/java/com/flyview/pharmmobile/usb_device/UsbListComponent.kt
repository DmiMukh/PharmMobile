package com.flyview.pharmmobile.usb_device

import com.flyview.pharmmobile.usb_device.state.UsbListState
import com.flyview.pharmmobile.usb_device.toolbar.UsbListToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface UsbListComponent {

    val toolBarComponent: UsbListToolbarComponent

    val state: StateFlow<UsbListState>
}