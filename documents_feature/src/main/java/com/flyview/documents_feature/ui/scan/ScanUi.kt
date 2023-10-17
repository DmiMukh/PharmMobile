package com.flyview.documents_feature.ui.scan

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScanUi(component: ScanComponent) {

}

@Composable
fun ScanUiPreview(darkTheme: Boolean){

}

@Preview(name = "light")
@Composable
fun ScanUiPreviewLight(){
    ScanUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun ScanUiPreviewDark(){
    ScanUiPreview(darkTheme = false)
}