package com.flyview.inventory_feature.ui.details.toolbar

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

class RealDocumentDetailsToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentDetailsToolbarComponent {
    override val usbDeviceConnected: StateFlow<Boolean>
        get() = TODO("Not yet implemented")

    override fun onBackClick() = this.onBack.invoke()
    override fun onUsbDeviceConnectionClick() {
        TODO("Not yet implemented")
    }
}