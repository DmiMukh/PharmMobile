package com.flyview.documents_feature.ui.document

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
import com.flyview.documents_feature.ui.document.toolbar.DocumentToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentUi(component: DocumentComponent) {
    Scaffold(
        topBar = { DocumentToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            DocumentButton(
                text = "Сканирование",
                onClick = {}
            )

            DocumentButton(
                text = "Размещение",
                onClick = {}
            )

            DocumentButton(
                text = "Сборка",
                onClick = {}
            )
        }
    }
}

@Composable
fun DocumentButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = text,
            fontSize = 24.sp
        )
    }
}

@Composable
fun DocumentUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentUi(FakeDocumentComponent())
    }
}

@Preview("light")
@Composable
fun DocumentUiPreviewLight() {
    DocumentUiPreview(darkTheme = false)
}

@Preview("dark")
@Composable
fun DocumentUiPreviewDark() {
    DocumentUiPreview(darkTheme = true)
}