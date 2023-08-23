package com.flyview.inventory_feature.ui.main.dialog

import kotlinx.coroutines.flow.StateFlow

interface MainDialogComponent {

    val canClose: StateFlow<Boolean>

    val state: StateFlow<MainDialogState>

    val articulsLoadComplete: StateFlow<LoadState>
    val certificatesLoadComplete: StateFlow<LoadState>
    val marksLoadComplete: StateFlow<LoadState>

    fun onCloseClick()
    fun onDismissClick()
}