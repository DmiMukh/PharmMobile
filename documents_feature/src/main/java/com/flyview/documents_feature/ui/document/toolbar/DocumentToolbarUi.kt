package com.flyview.documents_feature.ui.document.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentToolbarUi(component: DocumentToolbarComponent) {

    val document = component.document.collectAsState()

    TopAppBar(
        title = {
            Text(
                text = document.value.typeName
                    .plus(" ")
                    .plus(document.value.number),
                overflow = TextOverflow.Ellipsis
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
        }
    )
}

@Composable
fun DocumentToolbarUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentToolbarUi(FakeDocumentToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun DocumentToolbarUiPreviewLight() {
    DocumentToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentToolbarUiPreviewDark() {
    DocumentToolbarUiPreview(darkTheme = true)
}