package com.flyview.mark_feature.ui.main.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme

@Composable
fun MainToolbarUi(component: MainToolbarComponent) {

}

@Composable
fun MainToolbarUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        MainToolbarUi(FakeMainToolbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun MainToolbarUiPreviewLight(){
    MainToolbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun MainToolbarUiPreviewDark(){
    MainToolbarUiPreview(darkTheme = true)
}