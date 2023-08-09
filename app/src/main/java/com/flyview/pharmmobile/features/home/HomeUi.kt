package com.flyview.pharmmobile.features.home

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(component: HomeComponent) {
    Scaffold {
        Button(onClick = component::onInventoryClick) {
            Text(text = "Инвентаризация")
        }
    }
}

@Composable
fun HomeUiPreview(darkTheme: Boolean) {
    PharmMobileTheme(
        darkTheme = darkTheme
    ) {
        HomeUi(FakeHomeComponent())
    }
}

@Preview(name = "light")
@Composable
fun HomeUiPreviewLight() {
    HomeUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun HomeUiPreviewDark() {
    HomeUiPreview(darkTheme = true)
}