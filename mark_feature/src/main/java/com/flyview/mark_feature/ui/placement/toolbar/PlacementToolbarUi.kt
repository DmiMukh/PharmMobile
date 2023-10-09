package com.flyview.mark_feature.ui.placement.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme

@Composable
fun PlacementToolbarUi(component: PlacementToolbarComponent) {

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