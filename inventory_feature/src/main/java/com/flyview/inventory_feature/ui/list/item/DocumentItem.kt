package com.flyview.inventory_feature.ui.list.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE
import com.flyview.core.utils.getCurrentLocalDateTime
import com.flyview.inventory_feature.domain.model.Document

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DocumentItem(
    model: Document,
    onClick: (Document) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .combinedClickable(
                onClick = { onClick(model) },
                onLongClick = { }
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column {
                Row {
                    Text(text = model.id.toString())
                    Text(
                        text = model.number,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Text(text = model.formattedCreationDate)
            }

            if (model.sended) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "ok",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(ICON_SIZE)
                )
            }
        }
    }
}

@Composable
fun DocumentItemPreview(darkTheme: Boolean) {
    val currentDT = getCurrentLocalDateTime()

    AppTheme(darkTheme = darkTheme) {
        DocumentItem(
            model = Document(
                id = 0,
                number = "test",
                formattedCreationDate = currentDT.toString(),
                sended = false
            ),
            onClick = {}
        )
    }
}

@Preview(name = "light")
@Composable
fun DocumentItemPreviewLight() {
    DocumentItemPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentItemPreviewDark() {
    DocumentItemPreview(darkTheme = true)
}