package com.flyview.pharmmobile.settings

import com.flyview.pharmmobile.settings.toolbar.SettingsToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface SettingsComponent {

    val host: StateFlow<String>

    val toolbarComponent: SettingsToolbarComponent

    fun onHostChanged(newValue: String)
    fun onSaveClick()
}