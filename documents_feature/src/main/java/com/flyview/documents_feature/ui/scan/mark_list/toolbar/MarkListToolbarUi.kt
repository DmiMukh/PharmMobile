package com.flyview.documents_feature.ui.scan.mark_list.toolbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkListToolbarUi(component: MarkListToolbarComponent) {
    TopAppBar(
        title = { /*TODO*/ }
    )
}

@Composable
fun MarkListToolbarUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        MarkListToolbarUi(FakeMarkListToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun MarkListToolbarUiPreviewLight() = MarkListToolbarUiPreview(darkTheme = false)

@Preview(name = "dark")
@Composable
fun MarkListToolbarUiPreviewDark() = MarkListToolbarUiPreview(darkTheme = true)