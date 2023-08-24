package com.flyview.inventory_feature.ui.main.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE
import kotlinx.coroutines.delay

@Composable
fun UploadDialogUi(component: MainDialogComponent) {

    val canClose = component.canClose.collectAsState()

    val articulsLoaded = component.articulsLoadComplete.collectAsState()
    val certificatesLoaded = component.certificatesLoadComplete.collectAsState()
    val marksLoaded = component.marksLoadComplete.collectAsState()

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
        title = { Text(text = "Загрузка данных") },
        text = {
            Column {
                Row {
                    Text(text = "Время:", modifier = Modifier.padding(end = 4.dp))
                    Text(text = ticks.value.toString())
                    Text(text = "сек.", modifier = Modifier.padding(start = 4.dp))
                }
                
                Progress(
                    title = "Товары",
                    state = articulsLoaded.value
                )

                Progress(
                    title = "Серии",
                    state = certificatesLoaded.value
                )

                Progress(
                    title = "Маркировка",
                    state = marksLoaded.value
                )
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

@Composable
fun Progress(
    title: String,
    state: LoadState
) {
    Row(
        modifier = Modifier.padding(4.dp)
    ) {
        when (state) {
            is LoadState.Loading -> CircularProgressIndicator(modifier = Modifier.size(ICON_SIZE))
            is LoadState.Cancel -> Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "cancel",
                modifier = Modifier.size(ICON_SIZE)
            )

            is LoadState.OK -> Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "done",
                modifier = Modifier.size(ICON_SIZE)
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun UploadDialogUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        UploadDialogUi(FakeMainDialogComponent())
    }
}

@Preview(name = "light")
@Composable
fun UploadDialogUiPreviewLight() {
    UploadDialogUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun UploadDialogUiPreviewDark() {
    UploadDialogUiPreview(darkTheme = true)
}