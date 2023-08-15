package com.flyview.core.barcode.domain

import kotlinx.coroutines.flow.StateFlow

interface BarcodeReader {

    val connected: StateFlow<Boolean>
    fun onConnect()
    fun onDisconnect()
    fun switchConnection()
}