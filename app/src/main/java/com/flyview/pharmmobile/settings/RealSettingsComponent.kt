package com.flyview.pharmmobile.settings

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.flyview.pharmmobile.settings.toolbar.RealSettingsToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealSettingsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, SettingsComponent {

    private val componentInstance = instanceKeeper.getOrCreate(::SomeLogic)

    override val agent = componentInstance.agent
    override val firm = componentInstance.firm
    override val host = componentInstance.host
    override val stock = componentInstance.stock

    override val toolbarComponent = RealSettingsToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onSave = { this.onSaveClick() }
    )

    override fun onAgentChange(newValue: String) {
        this.agent.value = newValue
    }

    override fun onFirmChange(newValue: String) {
        this.firm.value = newValue
    }

    override fun onHostChange(newValue: String) {
        this.host.value = newValue
    }

    override fun onStockChange(newValue: String) {
        this.stock.value = newValue
    }

    override fun onSaveClick() {
        TODO("Not yet implemented")
    }

    private class SomeLogic : InstanceKeeper.Instance {

        val agent = MutableStateFlow("")
        val firm = MutableStateFlow("")
        val host = MutableStateFlow("")
        val stock = MutableStateFlow("")

        override fun onDestroy() = Unit
    }
}