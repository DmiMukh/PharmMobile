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
import com.flyview.core.theme.AppTheme
import com.flyview.inventory_feature.ui.main.toolbar.MainToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(component: MainComponent) {

    val dataDate = component.formattedDateOfData.collectAsState()

    Scaffold(
        topBar = { MainToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row {
                    Text(
                        text = "Данные от",
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(text = dataDate.value)
                }
            }

            Button(
                onClick = component::onDocumentsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = "Ведомости")
            }

            Button(
                onClick = component::onUploadDataClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = "Загрузить данные")
            }

            Button(
                onClick = component::onClearDataClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = "Удалить данные")
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