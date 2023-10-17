package com.flyview.documents_feature.ui.main.toolbar

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentScope
import com.flyview.core.utils.getCurrentLocalDateTime
import com.flyview.documents_feature.ui.main.use_case.FormatDateUseCase
import com.flyview.documents_feature.ui.main.use_case.GetDatePickerDialogUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.LocalDate

class RealMainToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onRefresh: (LocalDate) -> Unit
) : ComponentContext by componentContext, MainToolbarComponent {

    private val date = MutableStateFlow(getCurrentLocalDateTime().date)
    override val formattedDate = MutableStateFlow("")

    override fun onBackClick() = this.onBack.invoke()

    override fun onDateClick(context: Context) {
        GetDatePickerDialogUseCase().invoke(
            context = context,
            date = this.date.value,
            onSetDate = { new_date -> this.date.value = new_date }
        ).show()
    }

    override fun onRefreshClick() = this.onRefresh.invoke(this.date.value)

    init {
        date.onEach {
            formattedDate.value = FormatDateUseCase().invoke(it)
            this.onRefresh.invoke(it)
        }.launchIn(componentScope)
    }
}