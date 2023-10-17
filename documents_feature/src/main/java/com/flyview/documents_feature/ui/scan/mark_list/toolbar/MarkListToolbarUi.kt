package com.flyview.documents_feature.ui.scan.mark_list.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.R
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkListToolbarUi(component: MarkListToolbarComponent) {
    TopAppBar(
        title = { /*TODO*/ },
        navigationIcon = {
            IconButton(onClick = component::onBackClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "menu",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
        },
        actions = {
            IconButton(onClick = component::onSaveClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = "save",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
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