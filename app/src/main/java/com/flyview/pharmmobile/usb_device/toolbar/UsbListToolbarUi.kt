package com.flyview.pharmmobile.usb_device.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsbListToolbarUi(component: UsbListToolbarComponent) {
    TopAppBar(
        title = { Text("Устройства") },
        navigationIcon = {
            IconButton(onClick = component::onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrow_back"
                )
            }
        },
        actions = {
            IconButton(onClick = component::onRefreshClick) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "arrow_back"
                )
            }
        }
    )
}

@Composable
fun UsbListToolbarUiPreview(darkTheme: Boolean) {
    AppTheme (darkTheme = darkTheme) {
        UsbListToolbarUi(FakeUsbListToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun UsbListToolbarUiPreviewLight(){
    UsbListToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun UsbListToolbarUiPreviewDark(){
    UsbListToolbarUiPreview(darkTheme = true)
}