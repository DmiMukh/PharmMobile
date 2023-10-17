package com.flyview.documents_feature.ui.scan.mark_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme
import com.flyview.documents_feature.ui.scan.mark_list.navbar.MarkListNavbarUi
import com.flyview.documents_feature.ui.scan.mark_list.toolbar.MarkListToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkListUi(component: MarkListComponent) {
    Scaffold(
        topBar = { MarkListToolbarUi(component.toolbarComponent) },
        bottomBar = { MarkListNavbarUi(component.navbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            /*
            LazyColumn {
                state.items.forEach { model ->

                }
            }
            */
        }
    }
}

@Composable
fun MarkListUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        MarkListUi(FakeMarkListComponent())
    }
}

@Preview(name = "light")
@Composable
fun MarkListUiPreviewLight() = MarkListUiPreview(darkTheme = false)

@Preview(name = "dark")
@Composable
fun MarkListUiPreviewDark() = MarkListUiPreview(darkTheme = true)
