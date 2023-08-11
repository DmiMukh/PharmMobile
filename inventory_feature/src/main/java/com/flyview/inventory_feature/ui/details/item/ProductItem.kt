package com.flyview.inventory_feature.ui.details.item

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
import com.flyview.inventory_feature.domain.Articul
import com.flyview.inventory_feature.domain.Certificate
import com.flyview.inventory_feature.domain.Product
import java.math.RoundingMode

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItem(
    model: Product,
    onClick: (Product) -> Unit
) {

    fun test(quantity: Double, divisibility: Int): String {
        val packs = (model.quantity * model.articul.divisibility)
            .toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toInt()
        val mainQty = packs.div(model.articul.divisibility)
        val subQty = packs.mod(model.articul.divisibility)

        return mainQty.toString()
            .plus(" (")
            .plus(subQty.toString())
            .plus(" / ")
            .plus(model.articul.divisibility.toString())
            .plus(")")
    }

    val quantity = test(model.quantity, model.articul.divisibility)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .combinedClickable(
                onClick = { onClick(model) },
                onLongClick = { }
            ),
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
            Row {
                Text(text = "Сканировано:", modifier = Modifier.padding(end = 4.dp))
                Text(text = quantity)
            }
        }
    }
}

@Composable
fun ProductItemPreview(darkTheme: Boolean) {

    val model = Product(
        articul = Articul(
            id = 0,
            name = "Аспирин-Ультра 150 мг №30",
            producer = "",
            divisibility = 4
        ),
        certificate = Certificate(
            id = 0,
            name = "123456&0"
        ),
        quantity = 1.250
    )

    AppTheme(darkTheme = darkTheme) {
        ProductItem(
            model = model,
            onClick = {}
        )
    }
}

@Preview(name = "light")
@Composable
fun ProductItemPreviewLight() {
    ProductItemPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun ProductItemPreviewDark() {
    ProductItemPreview(darkTheme = true)
}