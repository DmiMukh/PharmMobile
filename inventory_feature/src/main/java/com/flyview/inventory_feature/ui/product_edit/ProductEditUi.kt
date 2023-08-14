package com.flyview.inventory_feature.ui.product_edit

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditUi(component: ProductEditComponent) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ },
        title = { /*TODO*/ },
        text = {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "") }
            )
        }
    )
}

@Composable
fun ProductEditUiPreview(darkTheme: Boolean) {
    AppTheme (darkTheme = darkTheme) {
        ProductEditUi(FakeProductEditComponent())
    }
}

@Preview(name = "light")
@Composable
fun ProductEditUiPreviewLight(){
    ProductEditUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun ProductEditUiPreviewDark(){
    ProductEditUiPreview(darkTheme = true)
}