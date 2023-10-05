package com.flyview.mark_feature.ui.main

import com.flyview.mark_feature.ui.main.toolbar.FakeMainToolbarComponent

class FakeMainComponent : MainComponent {
    override val toolbarComponent = FakeMainToolbarComponent()
}