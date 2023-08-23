package com.flyview.inventory_feature.ui.main.dialog

import kotlinx.coroutines.flow.MutableStateFlow

class FakeMainDialogComponent: MainDialogComponent {
    override val canClose = MutableStateFlow(false)

    override val state = MutableStateFlow(MainDialogState.Hidden)

    override val articulsLoadComplete = MutableStateFlow(LoadState.Loading)
    override val certificatesLoadComplete = MutableStateFlow(LoadState.OK)
    override val marksLoadComplete = MutableStateFlow(LoadState.Cancel)
    override fun onCloseClick() = Unit
    override fun onDismissClick() = Unit
}