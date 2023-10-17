package com.flyview.documents_feature.ui.main

import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.main.toolbar.MainToolbarComponent
import kotlinx.coroutines.flow.StateFlow

interface MainComponent {

    val viewState: StateFlow<MainViewState>
    val toolbarComponent: MainToolbarComponent

    fun onItemClick(item: Document)
}