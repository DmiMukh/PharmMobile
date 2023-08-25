package com.flyview.inventory_feature.ui.product_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditUi(component: ProductEditComponent) {

    val divQuantity = component.divQuantity.collectAsState()
    val modQuantity = component.modQuantity.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(text = "Товар")
            Text(text = component.articul.name)

            Text(text = "Производитель")
            Text(text = component.articul.producer)

            Text(text = "Серия")
            Text(text = component.certificate.name)

            Text(text = "Количество")

            OutlinedTextField(
                value = divQuantity.value,
                onValueChange = component::onChangeDivQuantity,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Целые") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = CircleShape
            )

            Row {
                OutlinedTextField(
                    value = modQuantity.value,
                    onValueChange = component::onChangeModQuantity,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 2.dp),
                    enabled = component.isDivisibility,
                    label = { Text(text = "Деленые") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = CircleShape
                )

                OutlinedTextField(
                    value = component.articul.divisibility.toString(),
                    onValueChange = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp),
                    readOnly = true,
                    label = { Text(text = "Делимость") },
                    shape = CircleShape
                )
            }


            Button(
                onClick = component::onSaveClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Сохранить")
            }

            OutlinedButton(
                onClick = component::onCancelClick,
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