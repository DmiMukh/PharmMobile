package com.flyview.inventory_feature.ui.test

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.message.data.MessageService
import com.flyview.core.message.domain.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlin.coroutines.EmptyCoroutineContext

class RealTestCameraComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val messageService: MessageService
) : ComponentContext by componentContext, TestCameraComponent {

    private val coroutineScope = CoroutineScope(EmptyCoroutineContext + Dispatchers.IO)

    override val scanData = MutableStateFlow("")
    override val scanFlag = MutableStateFlow(true)
    override fun setScanData(newValue: String) {
        this.scanData.value = newValue
    }

    override fun setScanFlag(newValue: Boolean) {
        this.scanFlag.value = newValue
    }

    init {
        scanData.onEach {
            it?.let {
                if (it.isNotEmpty()){
                    messageService.showMessage(Message(text = it))
                    scanData.value = ""
                }
            }
        }.launchIn(this.coroutineScope)
    }
}