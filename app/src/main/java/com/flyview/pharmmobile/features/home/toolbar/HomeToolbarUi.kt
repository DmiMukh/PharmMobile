package com.flyview.pharmmobile.features.home.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE
import com.flyview.pharmmobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolbarUi(component: HomeToolbarComponent) {
    TopAppBar(
        title = { Text(text = "Фарм Звонилка") },
        actions = {
            IconButton(onClick = component::onBarcodeReaderClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_barcode_reader),
                    contentDescription = "barcode_reader_settings",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }

            IconButton(onClick = component::onSettingsClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "settings",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
        }
    )
}

@Composable
fun HomeToolbarUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
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