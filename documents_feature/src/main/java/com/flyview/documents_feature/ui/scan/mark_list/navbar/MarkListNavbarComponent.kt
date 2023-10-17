package com.flyview.documents_feature.ui.scan.mark_list.navbar

import kotlinx.coroutines.flow.StateFlow

interface MarkListNavbarComponent {

    val viewState: StateFlow<MarkListTab>

    fun onClick(tab: MarkListTab)
}