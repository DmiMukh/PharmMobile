package com.flyview.inventory_feature.ui.list.toolbar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeDocumentListToolbarComponent : DocumentListToolbarComponent {
    override val menuExpanded = MutableStateFlow(false)

    override fun onBackClick() = Unit
    override fun onCollapseMenuClick() = Unit
    override fun onExpandMenuClick() = Unit
    override fun onSendDataClick() = Unit
}