package com.flyview.core.domain

interface AudioPlayer {
    suspend fun play(sound: AppSound)
}