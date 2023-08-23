package com.flyview.inventory_feature.ui.details.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE
import com.flyview.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentDetailsToolbarUi(component: DocumentDetailsToolbarComponent) {

    val connected = component.barcodeReaderConnected.collectAsState()

    TopAppBar(
        title = {
            Row {
                Text(
                    text = "Ведомость",
                    modifier = Modifier.padding(end = 4.dp)
                )
                //Text(text = model.value.id.toString())
            }
        },
        navigationIcon = {
            IconButton(onClick = component::onBackClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
        },
        actions = {
            IconButton(onClick = component::onUsbDeviceConnectionClick) {
                Icon(
                    painter = painterResource(
                        id = if (connected.value) R.drawable.ic_usb_on
                        else R.drawable.ic_usb_off
                    ),
                    contentDescription = "usb_device_connection",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
        }
    )
}

@Composable
fun DocumentDetailsToolbarUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentDetailsToolbarUi(FakeDocumentDetailsToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun DocumentDetailsToolbarUiPreviewLight() {
    DocumentDetailsToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentDetailsToolbarUiPreviewDark() {
    DocumentDetailsToolbarUiPreview(darkTheme = true)
}