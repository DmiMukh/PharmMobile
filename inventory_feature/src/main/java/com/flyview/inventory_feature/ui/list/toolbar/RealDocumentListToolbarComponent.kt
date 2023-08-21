package com.flyview.inventory_feature.ui.list.toolbar

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow

class RealDocumentListToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, DocumentListToolbarComponent {

    override val menuExpanded = MutableStateFlow(false)

    override fun onBackClick() = this.onBack.invoke()
    override fun onCollapseMenuClick() {
        this.onSetMenuExpand(false)
        Log.d("EXPANDED", this.menuExpanded.value.toString())
    }

    override fun onExpandMenuClick() {
        this.onSetMenuExpand(true)
        Log.d("EXPANDED", this.menuExpanded.value.toString())
    }

    override fun onSendDataClick() {
        TODO("Not yet implemented")
        TODO("")
    }

    private fun onSetMenuExpand(newValue: Boolean) {
        this.menuExpanded.value = newValue
    }
}