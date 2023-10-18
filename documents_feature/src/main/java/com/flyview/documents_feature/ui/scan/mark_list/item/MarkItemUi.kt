package com.flyview.documents_feature.ui.scan.mark_list.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.documents_feature.domain.model.MarkCode

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarkItemUi(
    model: MarkCode,
    onClick: (MarkCode) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 4.dp, end = 12.dp)
            .combinedClickable(
                onClick = { onClick(model) },
                onLongClick = { }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

        }
    }
}

@Composable
fun MarkItemUiPreview(darkTheme: Boolean){
    AppTheme (darkTheme = darkTheme){
        MarkItemUi(
            model = MarkCode(),
            onClick = {}
        )
    }
}

@Preview(name = "light")
@Composable
fun MarkItemUiPreviewLight() = MarkItemUiPreview(darkTheme = false)

@Preview(name = "dark")
@Composable
fun MarkItemUiPreviewDark() = MarkItemUiPreview(darkTheme = true)