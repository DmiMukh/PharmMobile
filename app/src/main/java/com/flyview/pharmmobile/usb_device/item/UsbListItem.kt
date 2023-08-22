package com.flyview.pharmmobile.usb_device.item

import android.hardware.usb.UsbDevice
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsbListItem(
    model: Pair<String, UsbDevice>,
    onClick: (UsbDevice) -> Unit
) {
    Card(
        onClick = { onClick(model.second) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = model.first)

            Row {
                Row(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Vendor Id:"
                    )
                    Text(
                        text = model.second.vendorId.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Row(modifier = Modifier.weight(1f)) {
                    Text(text = "Name:")
                    Text(
                        text = model.second.manufacturerName.orEmpty(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Row {
                Row(modifier = Modifier.weight(1f)) {
                    Text(text = "Product Id:")
                    Text(
                        text = model.second.productId.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Row(modifier = Modifier.weight(1f)) {
                    Text(text = "Name:")
                    Text(
                        text = model.second.productName.orEmpty(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
