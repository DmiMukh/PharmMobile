package com.flyview.pharmmobile.settings

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.flyview.pharmmobile.settings.toolbar.RealSettingsToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class RealSettingsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, SettingsComponent {

    private val componentInstance = instanceKeeper.getOrCreate(::SomeLogic)

    override val host = componentInstance.host

    override val toolbarComponent = RealSettingsToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onSave = { this.onSaveClick() }
    )

    override fun onHostChanged(newValue: String) {
        host.value = newValue
    }

    override fun onSaveClick() {
        TODO("Not yet implemented")
    }

    private class SomeLogic : InstanceKeeper.Instance {
        val host = MutableStateFlow("")
        override fun onDestroy() = Unit
    }
}