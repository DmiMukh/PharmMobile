package com.flyview.core.barcode_reader.domain

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.flow.StateFlow

interface BarcodeReader {

    val connected: StateFlow<Boolean>
    fun onConnect()
    fun onDisconnect()
    fun switchConnection()
}