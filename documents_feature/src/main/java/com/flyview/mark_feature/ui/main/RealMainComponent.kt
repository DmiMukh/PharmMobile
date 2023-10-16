package com.flyview.mark_feature.ui.main

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentScope
import com.flyview.mark_feature.ui.main.toolbar.RealMainToolbarComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RealMainComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
): ComponentContext by componentContext, MainComponent {

    override val viewState: MutableStateFlow<MainState> = MutableStateFlow(MainState.Idle)
    override val toolbarComponent = RealMainToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onRefresh = { fetchDocuments() }
    )

    private fun fetchDocuments(){

        if (MainState.Loading == viewState.value) return
        viewState.value = MainState.Loading

        componentScope.launch {
            try {
                delay(1000)
                viewState.value = MainState.Idle
                /*
                val items = emptyList<String>()

                if (items.size == 0) {
                    viewState.value = MainState.NoItems
                    return@launch
                }

                viewState.value = MainState.Display(items)
                return@launch
                */
            } catch (ex: Exception) {
                viewState.value = MainState.Error(ex.localizedMessage.orEmpty())
                return@launch
            }
        }
    }
}