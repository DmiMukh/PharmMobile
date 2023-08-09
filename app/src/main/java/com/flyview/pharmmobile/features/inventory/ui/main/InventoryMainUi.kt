package com.flyview.pharmmobile.features.inventory.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryMainUi(component: InventoryMainComponent) {
    Scaffold {
        Column {
            Text("InventoryMainUi")
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