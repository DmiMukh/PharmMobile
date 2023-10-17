package com.flyview.documents_feature.ui.main.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.documents_feature.domain.model.Document

@Composable
fun MainItemUi(
    model: Document,
    onClick: (Document) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentSize(align = Alignment.TopStart)
            .clickable(onClick = { onClick(model) })
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                MainItemText(text = model.typeName, modifier = Modifier.align(Alignment.End))
                MainItemText(text = model.number, modifier = Modifier.align(Alignment.End))
                Text(text = "сумма")
                MainItemText(
                    text = model.totalSum.toString(),
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Text(text = "отправитель")
                MainItemText(text = model.senderName)
                Text(text = "получатель")
                MainItemText(text = model.recieverName)
            }
        }
    }
}

@Composable
fun MainItemText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = text,
        overflow = TextOverflow.Ellipsis,
        softWrap = false,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MainItemUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        MainItemUi(
            model = Document(),
            onClick = {}
        )
    }
}

@Preview(name = "light")
@Composable
fun MainItemUiPreviewLight() {
    MainItemUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun MainItemUiPreviewDark() {
    MainItemUiPreview(darkTheme = true)
}