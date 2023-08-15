package com.flyview.inventory_feature.ui.product_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditUi(component: ProductEditComponent) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(text = "Товар")
            Text(text = "Аспирин кардио")

            Text(text = "Производитель")
            Text(text = "")

            Text(text = "Серия")
            Text(text = "")

            Text(text = "Количество")

            OutlinedTextField(
                value = "12",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Целые") },
                shape = CircleShape
            )

            OutlinedTextField(
                value = "121",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Деленые") },
                shape = CircleShape
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Сохранить")
            }

            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Отмена")
            }
        }
    }
}

@Composable
fun ProductEditUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        ProductEditUi(FakeProductEditComponent())
    }
}

@Preview(name = "light")
@Composable
fun ProductEditUiPreviewLight() {
    ProductEditUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun ProductEditUiPreviewDark() {
    ProductEditUiPreview(darkTheme = true)
}