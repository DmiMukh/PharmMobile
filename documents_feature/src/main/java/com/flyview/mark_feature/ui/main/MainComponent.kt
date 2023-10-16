package com.flyview.mark_feature.ui.main

import com.flyview.mark_feature.ui.main.toolbar.MainToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface MainComponent {

    val viewState: StateFlow<MainState>
    val toolbarComponent: MainToolbarComponent
}