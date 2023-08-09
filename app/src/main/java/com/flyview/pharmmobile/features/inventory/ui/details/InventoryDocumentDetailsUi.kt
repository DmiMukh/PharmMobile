package com.flyview.pharmmobile.features.inventory.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.features.inventory.ui.details.toolbar.InventoryDocumentDetailsToolbarUi
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryDocumentDetailsUi(component: InventoryDocumentDetailsComponent) {
    Scaffold(
        topBar = { InventoryDocumentDetailsToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun InventoryDocumentDetailsUiPreview(darkTheme: Boolean) {
    PharmMobileTheme(darkTheme = darkTheme) {
        InventoryDocumentDetailsUi(FakeInventoryDocumentDetailsComponent())
    }
}

@Preview(name = "light")
@Composable
fun InventoryDocumentDetailsUiPreviewLight() {
    InventoryDocumentDetailsUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun InventoryDocumentDetailsUiPreviewDark() {
    InventoryDocumentDetailsUiPreview(darkTheme = true)
}