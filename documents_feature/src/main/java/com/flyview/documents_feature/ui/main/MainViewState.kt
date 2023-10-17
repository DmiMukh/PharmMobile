package com.flyview.documents_feature.ui.main

import com.flyview.documents_feature.domain.model.Document

sealed class MainViewState {

    data class Display(val items: List<Document>) : MainViewState()
    data class Error (val msg: String): MainViewState()
    object Idle: MainViewState()
    object Loading: MainViewState()
    object NoItems: MainViewState()
}
