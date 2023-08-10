package com.flyview.inventory_feature.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme
import com.flyview.inventory_feature.ui.details.toolbar.DocumentDetailsToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentDetailsUi(component: DocumentDetailsComponent) {
    Scaffold(
        topBar = { DocumentDetailsToolbarUi(component.toolbarComponent) })
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
        }
    }
}

@Composable
fun DocumentDetailsUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentDetailsUi(FakeDocumentDetailsComponent())
    }
}

@Preview(name = "light")
@Composable
fun DocumentDetailsUiPreviewLight() {
    DocumentDetailsUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentDetailsUiPreviewDark() {
    DocumentDetailsUiPreview(darkTheme = false)
}