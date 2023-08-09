package com.flyview.pharmmobile.features.inventory.ui.main.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.core.utils.ICON_SIZE
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryMainToolbarUi(component: InventoryMainToolbarComponent) {
    TopAppBar(
        title = { Text(text = "Инвентаризация") },
        navigationIcon = {
            IconButton(onClick = component::onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrow_back",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
        }
    )
}

@Composable
fun InventoryMainToolbarUiPreview(darkTheme: Boolean) {
    PharmMobileTheme {
        InventoryMainToolbarUi(FakeInventoryMainToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun InventoryMainToolbarUiPreviewLight() {
    InventoryMainToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun InventoryMainToolbarUiPreviewDark() {
    InventoryMainToolbarUiPreview(darkTheme = true)
}