package com.flyview.pharmmobile.data.barcode_reader

enum class DecodeState(private val int: Int) {
    STATE_IDLE(0),
    STATE_DECODE(1)
}