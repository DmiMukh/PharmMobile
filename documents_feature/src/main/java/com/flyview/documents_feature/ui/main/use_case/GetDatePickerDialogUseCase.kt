package com.flyview.documents_feature.ui.main.use_case

import android.app.DatePickerDialog
import android.content.Context
import com.flyview.core.utils.KOTLINX_MONTH_DIFF
import kotlinx.datetime.LocalDate

class GetDatePickerDialogUseCase {
    /* KotlinX и либа Android по-разному начинают отчет месяца, разница 1 */
    fun invoke(context: Context, date: LocalDate, onSetDate: (LocalDate) -> Unit): DatePickerDialog {
        return DatePickerDialog(
            context,
            { _, year: Int, month: Int, day: Int ->
                onSetDate(
                    LocalDate(
                        year = year,
                        monthNumber = month.plus(KOTLINX_MONTH_DIFF),
                        dayOfMonth = day
                    )
                )
            }, date.year, date.month.ordinal, date.dayOfMonth
        )
    }
}