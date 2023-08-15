package com.flyview.core.message.ui

import com.flyview.core.message.domain.Message
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMessageComponent : MessageComponent {

    override val visibleMessage = MutableStateFlow(
        Message("Message")
    )

    override fun onActionClick() = Unit
}
