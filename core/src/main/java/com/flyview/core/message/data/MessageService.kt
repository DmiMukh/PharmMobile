package com.flyview.core.message.data

import com.flyview.core.message.domain.Message
import kotlinx.coroutines.flow.Flow

interface MessageService {

    val messageFlow: Flow<Message>

    fun showMessage(message: Message)
}