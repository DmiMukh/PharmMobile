package com.flyview.documents_feature.ui.main

import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.main.toolbar.FakeMainToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMainComponent: MainComponent {
    override val viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    override val toolbarComponent = FakeMainToolbarComponent()
    override fun onItemClick(item: Document) = Unit
}