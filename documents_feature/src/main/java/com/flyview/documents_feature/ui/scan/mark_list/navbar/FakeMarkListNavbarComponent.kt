package com.flyview.documents_feature.ui.scan.mark_list.navbar

import kotlinx.coroutines.flow.MutableStateFlow

class FakeMarkListNavbarComponent : MarkListNavbarComponent {
    override val viewState = MutableStateFlow(MarkListTab.PACK)
    override fun onClick(tab: MarkListTab) = Unit
}