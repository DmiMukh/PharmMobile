package com.flyview.documents_feature.ui.scan.mark_list.navbar

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RealMarkListNavbarComponent(
    componentContext: ComponentContext,
    private val onChange: (MarkListTab) -> Unit
) : ComponentContext by componentContext, MarkListNavbarComponent {

    override val viewState = MutableStateFlow(MarkListTab.PACK)
    override fun onClick(tab: MarkListTab) {
        if (viewState.value == tab) return
        viewState.value = tab
    }

    init {
        viewState.onEach { onChange(it) }.launchIn(componentScope)
    }
}