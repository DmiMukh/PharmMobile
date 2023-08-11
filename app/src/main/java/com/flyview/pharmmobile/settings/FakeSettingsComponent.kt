package com.flyview.pharmmobile.settings

import com.flyview.pharmmobile.settings.toolbar.FakeSettingsToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSettingsComponent: SettingsComponent {

    override val host = MutableStateFlow("127.0.0.1")

    override val toolbarComponent = FakeSettingsToolbarComponent()
    override fun onHostChanged(newValue: String) = Unit
    override fun onSaveClick() = Unit
}