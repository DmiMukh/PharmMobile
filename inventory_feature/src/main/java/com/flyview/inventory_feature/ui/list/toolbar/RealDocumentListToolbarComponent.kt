package com.flyview.inventory_feature.ui.list.toolbar

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow

class RealDocumentListToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onSendClick: () -> Unit
) : ComponentContext by componentContext, DocumentListToolbarComponent {

    override val menuExpanded = MutableStateFlow(false)

    override fun onBackClick() = this.onBack.invoke()
    override fun onCollapseMenuClick() = this.onSetMenuExpand(false)
    override fun onExpandMenuClick() = this.onSetMenuExpand(true)

    override fun onSendDataClick() = this.onSendClick.invoke()

    private fun onSetMenuExpand(newValue: Boolean) {
        this.menuExpanded.value = newValue
    }
}