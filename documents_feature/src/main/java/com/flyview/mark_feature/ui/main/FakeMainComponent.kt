package com.flyview.mark_feature.ui.main

import com.flyview.mark_feature.ui.main.toolbar.FakeMainToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMainComponent: MainComponent {
    override val viewState = MutableStateFlow<MainState>(MainState.Idle)
    override val toolbarComponent = FakeMainToolbarComponent()
}