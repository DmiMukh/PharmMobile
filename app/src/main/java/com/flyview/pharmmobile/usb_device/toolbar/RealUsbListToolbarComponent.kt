package com.flyview.pharmmobile.usb_device.toolbar

import com.arkivanov.decompose.ComponentContext

class RealUsbListToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onRefreshClick: () -> Unit
): ComponentContext by componentContext, UsbListToolbarComponent {
    override fun onBackClick() = this.onBack.invoke()
    override fun onRefreshClick() = this.onRefreshClick.invoke()
}