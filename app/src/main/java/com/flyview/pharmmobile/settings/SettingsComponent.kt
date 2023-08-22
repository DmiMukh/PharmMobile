package com.flyview.pharmmobile.settings

import com.flyview.pharmmobile.settings.toolbar.SettingsToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface SettingsComponent {

    val agent: StateFlow<String>
    val firm: StateFlow<String>
    val host: StateFlow<String>
    val stock: StateFlow<String>

    val toolbarComponent: SettingsToolbarComponent

    fun onAgentChange(newValue: String)
    fun onFirmChange(newValue: String)
    fun onHostChange(newValue: String)
    fun onStockChange(newValue: String)
    fun onSaveClick()
}