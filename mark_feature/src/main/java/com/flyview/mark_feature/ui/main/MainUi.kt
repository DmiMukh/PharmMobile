package com.flyview.mark_feature.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flyview.core.theme.AppTheme
import com.flyview.mark_feature.ui.main.toolbar.MainToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(component: MainComponent) {
    Scaffold(
        topBar = { MainToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Сканирование",
                    fontSize = 24.sp
                )
            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Размещение",
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

@Preview("light")
@Composable
fun MainUiPreviewLight() {
    MainUiPreview(darkTheme = false)
}

@Preview("dark")
@Composable
fun MainUiPreviewDark() {
    MainUiPreview(darkTheme = true)
}