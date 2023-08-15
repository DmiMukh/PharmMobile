package com.flyview.core.message.ui

import com.flyview.core.message.domain.Message
import kotlinx.coroutines.flow.StateFlow

interface MessageComponent {
    val visibleMessage: StateFlow<Message?>

    fun onActionClick()
}