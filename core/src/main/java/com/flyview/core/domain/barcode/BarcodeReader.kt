package com.flyview.core.domain.barcode

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.flow.StateFlow

interface BarcodeReader {

    val connected: StateFlow<Boolean>

    val intentFilter: IntentFilter
    fun onConnect()
    fun onDisconnect()
    fun onBroadcastReceive(context: Context?, intent: Intent?)

    fun switchConnection()
}