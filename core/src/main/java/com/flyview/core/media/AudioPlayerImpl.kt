package com.flyview.core.media

import android.content.Context
import android.media.MediaPlayer
import com.flyview.core.R

class AudioPlayerImpl(
    private val context: Context
) : AudioPlayer {
    private val mpBeep: MediaPlayer = MediaPlayer.create(this.context, R.raw.beep)
    private val mpNotification: MediaPlayer =
        MediaPlayer.create(this.context, R.raw.notification)
    private val mpError: MediaPlayer = MediaPlayer.create(this.context, R.raw.error)

    override suspend fun play(sound: AppSound) = when (sound) {
        AppSound.BEEP -> mpBeep
        AppSound.NOTIFICATION -> mpNotification
        AppSound.ERROR -> mpError
        AppSound.USB_OFF -> mpBeep
        AppSound.USB_ON -> mpBeep
    }.start()
}