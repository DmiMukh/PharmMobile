package com.flyview.mark_feature.ui.placement.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacementToolbarUi(component: PlacementToolbarComponent) {
    TopAppBar(
        title = { Text(text = "Документ") },
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
fun PlacementToolbarUiPreview(darkTheme: Boolean) {
    AppTheme (darkTheme = darkTheme) {
        PlacementToolbarUi(FakePlacementToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun PlacementToolbarUiPreviewLight() {
    PlacementToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun PlacementToolbarUiPreviewDark() {
    PlacementToolbarUiPreview(darkTheme = true)
}