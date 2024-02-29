package com.flyview.inventory_feature.ui.test

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeTestCameraComponent: TestCameraComponent {
    override val scanData = MutableStateFlow("")
    override val scanFlag = MutableStateFlow(true)
    override fun setScanData(newValue: String) = Unit
    override fun setScanFlag(newValue: Boolean) = Unit
}