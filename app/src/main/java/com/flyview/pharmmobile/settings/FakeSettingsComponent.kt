package com.flyview.pharmmobile.settings

import com.flyview.pharmmobile.settings.toolbar.FakeSettingsToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeSettingsComponent: SettingsComponent {

    override val agent = MutableStateFlow("20092")
    override val firm = MutableStateFlow("50")
    override val host = MutableStateFlow("127.0.0.1")
    override val stock = MutableStateFlow("156")

    override val toolbarComponent = FakeSettingsToolbarComponent()
    override fun onAgentChange(newValue: String) = Unit
    override fun onFirmChange(newValue: String) = Unit
    override fun onHostChange(newValue: String) = Unit
    override fun onStockChange(newValue: String) = Unit

    override fun onSaveClick() = Unit
}