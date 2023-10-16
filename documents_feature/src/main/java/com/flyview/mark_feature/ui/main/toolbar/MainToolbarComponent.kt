package com.flyview.mark_feature.ui.main.toolbar

import android.content.Context
import kotlinx.coroutines.flow.StateFlow

interface MainToolbarComponent {

    val formattedDate: StateFlow<String>
    fun onBackClick()
    fun onDateClick(context: Context)
    fun onRefreshClick()
}