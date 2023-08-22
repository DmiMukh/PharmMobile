package com.flyview.pharmmobile.usb_device.state

import android.hardware.usb.UsbDevice

sealed class UsbListState {
    data class Display(val items: List<Pair<String, UsbDevice>>) : UsbListState()
    object Loading : UsbListState()
    object NoItems : UsbListState()
}
