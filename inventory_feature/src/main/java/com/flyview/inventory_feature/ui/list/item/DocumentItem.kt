package com.flyview.inventory_feature.ui.list.item

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.inventory_feature.domain.Document

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
                Text(text = model.id.toString())
            }
        }
    }
}

@Composable
fun DocumentItemPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentItem(
            model = Document(
                id = 0,
                number = "tes"
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