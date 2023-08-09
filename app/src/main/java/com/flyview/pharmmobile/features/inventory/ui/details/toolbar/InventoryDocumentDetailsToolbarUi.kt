package com.flyview.pharmmobile.features.inventory.ui.details.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@Composable
fun InventoryDocumentDetailsToolbarUi(component: InventoryDocumentDetailsToolbarComponent) {

}

@Composable
fun InventoryDocumentDetailsToolbarUiPreview(darkTheme: Boolean) {
    PharmMobileTheme {
        InventoryDocumentDetailsToolbarUi(FakeInventoryDocumentDetailsToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun InventoryDocumentDetailsToolbarUiPreviewLight(){
    InventoryDocumentDetailsToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun InventoryDocumentDetailsToolbarUiPreviewDark(){
    InventoryDocumentDetailsToolbarUiPreview(darkTheme = true)
}