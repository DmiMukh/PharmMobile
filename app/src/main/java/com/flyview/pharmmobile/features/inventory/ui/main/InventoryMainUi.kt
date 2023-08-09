package com.flyview.pharmmobile.features.inventory.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.pharmmobile.features.inventory.ui.main.toolbar.InventoryMainToolbarUi
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryMainUi(component: InventoryMainComponent) {
    Scaffold(
        topBar = { InventoryMainToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            Button(
                onClick = component::onDocumentsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = "Ведомости")
            }
        }
    }
}

@Composable
fun InventoryMainUiPreview(darkTheme: Boolean) {
    PharmMobileTheme(
        darkTheme = darkTheme
    ) {
        InventoryMainUi(FakeInventoryMainComponent())
    }
}

@Preview(name = "light")
@Composable
fun InventoryMainUiPreviewLight() {
    InventoryMainUiPreview(darkTheme = false)
}

@Preview(name = "Dark")
@Composable
fun InventoryMainUiPreviewDark() {
    InventoryMainUiPreview(darkTheme = true)
}