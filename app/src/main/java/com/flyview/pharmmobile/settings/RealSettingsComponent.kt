package com.flyview.pharmmobile.settings

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.flyview.core.storage.SettingsStorage
import com.flyview.inventory_feature.domain.AGENT
import com.flyview.inventory_feature.domain.FIRM
import com.flyview.inventory_feature.domain.HOST
import com.flyview.inventory_feature.domain.STOCK
import com.flyview.pharmmobile.settings.toolbar.RealSettingsToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealSettingsComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val storage: SettingsStorage
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

        this.agent.value.toIntOrNull()?.let {
            storage.putInt(AGENT, it)
        }

        this.firm.value.toIntOrNull()?.let {
            storage.putInt(FIRM, it)
        }

        this.stock.value.toIntOrNull()?.let {
            storage.putInt(STOCK, it)
        }

        storage.putString(HOST, this.host.value)
    }

    private class SomeLogic : InstanceKeeper.Instance {

        var inited = false

        val agent = MutableStateFlow(AGENT)
        val firm = MutableStateFlow(FIRM)
        val host = MutableStateFlow(HOST)
        val stock = MutableStateFlow(STOCK)

        override fun onDestroy() = Unit
    }

    init {
        if (!componentInstance.inited) {
            this.agent.value = storage.getString(AGENT)
            this.firm.value = storage.getString(FIRM)
            this.host.value = storage.getString(HOST)
            this.stock.value = storage.getString(STOCK)
            componentInstance.inited = true
        }
    }
}