package com.flyview.pharmmobile.usb_device

import android.hardware.usb.UsbManager
import com.arkivanov.decompose.ComponentContext
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import com.flyview.core.utils.componentScope
import com.flyview.pharmmobile.usb_device.state.UsbListState
import com.flyview.pharmmobile.usb_device.toolbar.RealUsbListToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealUsbListComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val usbManager: UsbManager,
    private val messageService: MessageService
) : ComponentContext by componentContext, UsbListComponent {

    override val toolBarComponent = RealUsbListToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onRefreshClick = { this.refreshList() }
    )
    override val state = MutableStateFlow<UsbListState>(UsbListState.NoItems)
    private fun refreshList() {
        componentScope.launch {

            if (state.value == UsbListState.Loading) return@launch
            state.value = UsbListState.Loading

            try {
                val items = usbManager.deviceList.toList()
                state.value = UsbListState.Display(items = items)
                return@launch
            } catch (ex: Exception) {
                state.value = UsbListState.NoItems
                messageService.showMessage(Message(text = ex.localizedMessage.orEmpty()))
                return@launch
            }
        }
    }

    init {
        refreshList()
    }
}