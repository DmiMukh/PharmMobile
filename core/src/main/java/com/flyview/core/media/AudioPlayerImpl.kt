package com.flyview.core.media

import android.content.Context
import android.media.MediaPlayer
import com.flyview.core.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class AudioPlayerImpl(
    private val context: Context
) : AudioPlayer {

    private val coroutineScope = CoroutineScope(EmptyCoroutineContext + Dispatchers.IO)

    private val mpBeep: MediaPlayer = MediaPlayer.create(this.context, R.raw.beep)
    private val mpNotification: MediaPlayer = MediaPlayer.create(this.context, R.raw.notification)
    private val mpError: MediaPlayer = MediaPlayer.create(this.context, R.raw.error)
    private val mpUsbConnect: MediaPlayer = MediaPlayer.create(this.context, R.raw.usb_connect)
    private val mpUsbDisconnect: MediaPlayer = MediaPlayer.create(this.context, R.raw.usb_disconnect)

    override fun play(sound: AppSound) {
        coroutineScope.launch {
            when (sound) {
                AppSound.BEEP -> mpBeep
                AppSound.NOTIFICATION -> mpNotification
                AppSound.ERROR -> mpError
                AppSound.USB_OFF -> mpUsbDisconnect
                AppSound.USB_ON -> mpUsbConnect
            }.start()

            return@launch
        }
    }
}