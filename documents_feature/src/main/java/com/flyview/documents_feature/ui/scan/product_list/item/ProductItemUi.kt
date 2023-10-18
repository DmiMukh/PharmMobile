package com.flyview.documents_feature.ui.scan.product_list.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.documents_feature.domain.model.Product

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItemUi(
    model: Product,
    onClick: (Product) -> Unit
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
            Text(
                text = model.articul.name,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(text = "Серия:", modifier = Modifier.padding(end = 4.dp))
                Text(text = model.certificate.name)
            }
        }
    }
}

@Composable
fun ProductItemPreview(darkTheme: Boolean) {
    AppTheme (darkTheme = darkTheme){
        ProductItemUi(
            model = Product(),
            onClick = {}
        )
    }
}

@Preview(name = "light")
@Composable
fun ProductItemPreviewLight() = ProductItemPreview(darkTheme = false)

@Preview(name = "dark")
@Composable
fun ProductItemPreviewDark() = ProductItemPreview(darkTheme = true)