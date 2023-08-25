package com.flyview.inventory_feature.ui.list.dialog

import kotlinx.coroutines.flow.MutableStateFlow

class FakeListDialogComponent : ListDialogComponent {

    override val canClose = MutableStateFlow(false)
    override val sendedCount = MutableStateFlow(0)
    override val state = MutableStateFlow<ListDialogState>(ListDialogState.Hidden)
    override val totalCount = MutableStateFlow(0)

    override fun onCancelClick() = Unit
    override fun onCloseClick() = Unit
    override fun onDismissClick() = Unit
}