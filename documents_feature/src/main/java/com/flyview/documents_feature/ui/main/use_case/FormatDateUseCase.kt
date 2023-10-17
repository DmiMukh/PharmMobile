package com.flyview.documents_feature.ui.main.use_case

import android.os.Build
import kotlinx.datetime.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class FormatDateUseCase {
    private fun getMonthName(date: LocalDate) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date.month.getDisplayName(
                TextStyle.FULL,
                Locale("ru")
            )
        } else date.month.name

    fun invoke(date: LocalDate) = "${date.dayOfMonth} ${getMonthName(date)} ${date.year}"
}