package com.flyview.mark_feature.ui.main.toolbar

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentScope
import com.flyview.core.utils.getCurrentLocalDateTime
import com.flyview.mark_feature.ui.main.use_case.FormatDateUseCase
import com.flyview.mark_feature.ui.main.use_case.GetDatePickerDialogUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RealMainToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onRefresh: () -> Unit
) : ComponentContext by componentContext, MainToolbarComponent {

    private val date = MutableStateFlow(getCurrentLocalDateTime().date)
    override val formattedDate = MutableStateFlow("")

    override fun onBackClick() = this.onBack.invoke()

    override fun onDateClick(context: Context) {
        GetDatePickerDialogUseCase().invoke(
            context = context,
            date = this.date.value,
            onSetDate = {}
        )
    }

    override fun onRefreshClick() = this.onRefresh.invoke()

    init {
        date.onEach { FormatDateUseCase().invoke(it) }.launchIn(componentScope)
    }
}