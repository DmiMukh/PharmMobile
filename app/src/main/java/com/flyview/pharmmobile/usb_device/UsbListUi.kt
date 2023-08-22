package com.flyview.pharmmobile.usb_device

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.pharmmobile.usb_device.item.UsbListItem
import com.flyview.pharmmobile.usb_device.state.UsbListState
import com.flyview.pharmmobile.usb_device.toolbar.UsbListToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsbListUi(component: UsbListComponent) {

    val listState = component.state.collectAsState()

    Scaffold(
        topBar = { UsbListToolbarUi(component.toolBarComponent) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            when (val state = listState.value) {
                is UsbListState.Display -> {
                    Row {
                        Text(text = "Записей: ")
                        Text(text = "${state.items.size}")
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        state.items.forEach { model ->
                            item {
                                UsbListItem(
                                    model = model,
                                    onClick = { }
                                )
                            }
                        }
                    }
                }

                is UsbListState.Loading -> Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UsbListState.NoItems -> {}
            }
        }
    }
}

@Composable
fun UsbDeviceUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        UsbListUi(FakeUsbListComponent())
    }
}

@Preview(name = "light")
@Composable
fun UsbDeviceUiPreviewLight() {
    UsbDeviceUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun UsbDeviceUiPreviewDark() {
    UsbDeviceUiPreview(darkTheme = true)
}