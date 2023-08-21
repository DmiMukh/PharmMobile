package com.flyview.inventory_feature.ui.list.toolbar

import kotlinx.coroutines.flow.StateFlow

interface DocumentListToolbarComponent {

    val menuExpanded: StateFlow<Boolean>

    fun onBackClick()

    fun onCollapseMenuClick()
    fun onExpandMenuClick()
    fun onSendDataClick()
}