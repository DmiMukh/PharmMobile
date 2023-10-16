package com.flyview.mark_feature.ui.main.toolbar

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMainToolbarComponent : MainToolbarComponent {

    override val formattedDate = MutableStateFlow("1 апреля 2022")

    override fun onBackClick() = Unit
    override fun onDateClick(context: Context) = Unit
    override fun onRefreshClick() = Unit
}