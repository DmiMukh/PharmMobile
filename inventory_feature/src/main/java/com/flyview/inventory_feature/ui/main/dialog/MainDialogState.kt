package com.flyview.inventory_feature.ui.main.dialog

sealed class MainDialogState {

    object Hidden: MainDialogState()
    object UploadData: MainDialogState()
}

sealed class LoadState {
    object Cancel: LoadState()
    object Loading: LoadState()
    object OK: LoadState()
}
