package com.flyview.inventory_feature.ui.test

import kotlinx.coroutines.flow.StateFlow

interface TestCameraComponent {

    val scanData: StateFlow<String>

    val scanFlag: StateFlow<Boolean>

    fun setScanData(newValue: String)

    fun setScanFlag(newValue: Boolean)
}