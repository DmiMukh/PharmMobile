package com.flyview.inventory_feature.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flyview.core.theme.AppTheme
import com.flyview.inventory_feature.ui.main.dialog.MainDialogState
import com.flyview.inventory_feature.ui.main.dialog.UploadDialogUi
import com.flyview.inventory_feature.ui.main.toolbar.MainToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(component: MainComponent) {

    val dataDate = component.formattedDateOfData.collectAsState()
    val dialogState = component.dialogComponent.state.collectAsState()

    if (dialogState.value != MainDialogState.Hidden)
        UploadDialogUi(component.dialogComponent)

    Scaffold(
        topBar = { MainToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            Row (
                modifier = Modifier.padding(12.dp)
            ) {
                if (dataDate.value.isEmpty()) {
                    Text(text = "Данные не загружены")
                } else {
                    Text(
                        text = "Данные от",
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(text = dataDate.value)
                }
            }

            Button(
                onClick = component::onDocumentsClick,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Ведомости",
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = component::onUploadDataClick,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Загрузить данные",
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = component::onClearDataClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Удалить данные",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun MainUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        MainUi(FakeMainComponent())
    }
}

@Preview(name = "light")
@Composable
fun MainUiPreviewLight() {
    MainUiPreview(darkTheme = false)
}

@Preview(name = "Dark")
@Composable
fun MainUiPreviewDark() {
    MainUiPreview(darkTheme = true)
}