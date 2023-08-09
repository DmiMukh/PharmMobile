package com.flyview.pharmmobile.features.inventory.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.features.inventory.ui.list.toolbar.InventoryDocumentListToolbarUi
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryDocumentListUi(component: InventoryDocumentListComponent) {

    Scaffold (
        topBar = { InventoryDocumentListToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun InventoryDocumentListUiPreview(darkTheme: Boolean) {
    PharmMobileTheme (darkTheme = darkTheme) {
        InventoryDocumentListUi(FakeInventoryDocumentListComponent())
    }
}

@Preview(name = "light")
@Composable
fun InventoryDocumentListUiPreviewLight() {
    InventoryDocumentListUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun InventoryDocumentListUiPreviewDark() {
    InventoryDocumentListUiPreview(darkTheme = true)
}