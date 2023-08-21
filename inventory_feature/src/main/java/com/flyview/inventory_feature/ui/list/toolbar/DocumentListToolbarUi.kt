package com.flyview.inventory_feature.ui.list.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentListToolbarUi(component: DocumentListToolbarComponent) {

    val expanded = component.menuExpanded.collectAsState()

    TopAppBar(
        title = {
            Text(
                text = "Документы"
            )
        },
        navigationIcon = {
            IconButton(onClick = component::onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
        },
        actions = {
            IconButton(onClick = component::onExpandMenuClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more_vert"
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = component::onCollapseMenuClick
            ) {
                DropdownMenuItem(
                    text = { Text("Отправить на сервер") },
                    onClick = component::onSendDataClick
                )
                DropdownMenuItem(
                    text = { Text("ТЕС") },
                    onClick = { }
                )
            }
        }
    )
}

@Composable
fun DocumentListToolbarUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentListToolbarUi(FakeDocumentListToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun DocumentListToolbarUiPreviewLight() {
    DocumentListToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentListToolbarUiPreviewDark() {
    DocumentListToolbarUiPreview(darkTheme = true)
}