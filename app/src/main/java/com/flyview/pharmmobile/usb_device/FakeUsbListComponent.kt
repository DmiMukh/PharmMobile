package com.flyview.pharmmobile.usb_device

import com.flyview.pharmmobile.usb_device.state.UsbListState
import com.flyview.pharmmobile.usb_device.toolbar.FakeUsbListToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class FakeUsbListComponent: UsbListComponent {
    override val toolBarComponent = FakeUsbListToolbarComponent()
    override val state = MutableStateFlow(UsbListState.NoItems)
}