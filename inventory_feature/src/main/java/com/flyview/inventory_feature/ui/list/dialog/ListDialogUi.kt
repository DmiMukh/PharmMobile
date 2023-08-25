package com.flyview.inventory_feature.ui.list.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyview.core.utils.ICON_SIZE
import kotlinx.coroutines.delay

@Composable
fun ListDialogUi(component: ListDialogComponent) {

    val sendedCount = component.sendedCount.collectAsState()
    val totalCount = component.totalCount.collectAsState()
    val canClose = component.canClose.collectAsState()
    val ticks = remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = component::onDismissClick,
        confirmButton = {
            Button(
                onClick = component::onCloseClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                enabled = canClose.value
            ) {
                Text(text = "ОК")
            }
        },
        title = { Text(text = "Отправка документов") },
        text = {
            Column {
                if (canClose.value) {
                    Icon(
                        imageVector = if (sendedCount.value.equals(totalCount.value))
                            Icons.Default.CheckCircle else Icons.Default.Close,
                        contentDescription = "ok",
                        modifier = Modifier
                            .size(ICON_SIZE)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(ICON_SIZE)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                }

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(text = "Время:", modifier = Modifier.padding(end = 4.dp))
                    Text(text = ticks.value.toString())
                    Text(text = "сек.", modifier = Modifier.padding(start = 4.dp))
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        while (true) {
            for (i in 1..10) {
                delay(100)
                if (canClose.value) return@LaunchedEffect
            }
            ticks.value++
        }
    }
}