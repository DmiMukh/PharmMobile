package com.flyview.core.media

interface AudioPlayer {
    suspend fun play(sound: AppSound)
}