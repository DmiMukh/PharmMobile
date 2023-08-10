package com.flyview.core.domain

sealed class AppSound {
    object BEEP : AppSound()
    object NOTIFICATION : AppSound()
    object ERROR : AppSound()

    @Suppress("ClassName")
    object USB_ON : AppSound()
    @Suppress("ClassName")
    object USB_OFF : AppSound()
}
