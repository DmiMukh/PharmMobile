package com.flyview.inventory_feature.ui.main

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
import com.flyview.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(component: MainComponent) {
    Scaffold(
        topBar = { /*MainToolbarUi(component.toolbarComponent)*/ }
    ) { paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            Button(
                onClick = { /*component::onDocumentsClick*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = "Ведомости")
            }
        }
    }
}

@Composable
fun MainUiPreview(darkTheme: Boolean) {
    AppTheme(
        darkTheme = darkTheme
    ) {
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