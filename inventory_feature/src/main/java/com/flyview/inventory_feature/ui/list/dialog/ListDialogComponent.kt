package com.flyview.inventory_feature.ui.list.dialog

import kotlinx.coroutines.flow.StateFlow

interface ListDialogComponent {

    val canClose: StateFlow<Boolean>
    val sendedCount: StateFlow<Int>
    val state: StateFlow<ListDialogState>
    val totalCount: StateFlow<Int>

    fun onCancelClick()
    fun onCloseClick()
    fun onDismissClick()
}