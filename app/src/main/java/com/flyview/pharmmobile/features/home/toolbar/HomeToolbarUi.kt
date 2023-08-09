package com.flyview.pharmmobile.features.home.toolbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.R
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolbarUi(component: HomeToolbarComponent) {
    TopAppBar(
        title = { Text(text = "Фарм Звонилка") },
        actions = {
            IconButton(onClick = component::onBarcodeReaderClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_barcode_reader),
                    contentDescription = "barcode_reader_settings"
                )
            }
        }
    )
}

@Composable
fun HomeToolbarUiPreview(darkTheme: Boolean) {
    PharmMobileTheme(darkTheme = darkTheme) {
        HomeToolbarUi(FakeHomeToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun HomeToolbarUiPreviewLight() {
    HomeToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun HomeToolbarUiPreviewDark() {
    HomeToolbarUiPreview(darkTheme = true)
}