package com.flyview.mark_feature.ui.main

sealed class MainState {

    data class Display(val items: List<String>) : MainState()
    data class Error (val msg: String): MainState()
    object Idle: MainState()
    object Loading: MainState()
    object NoItems: MainState()
}
