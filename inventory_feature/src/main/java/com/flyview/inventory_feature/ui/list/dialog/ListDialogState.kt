package com.flyview.inventory_feature.ui.list.dialog

sealed class ListDialogState {

    object Hidden : ListDialogState()

    object SendDocuments: ListDialogState()
}
