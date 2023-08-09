package com.flyview.pharmmobile.features.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.pharmmobile.features.settings.toolbar.SettingsToolbarUi
import com.flyview.pharmmobile.ui.theme.PharmMobileTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsUi(component: SettingsComponent) {
    Scaffold(
        topBar = { SettingsToolbarUi(component.toolbarComponent) }
    ) {
        Column {
            Text(text = "SettingsUi")
        }
    }
}

@Composable
fun SettingsUiPreview(darkTheme: Boolean) {
    PharmMobileTheme(darkTheme = darkTheme) {
        SettingsUi(FakeSettingsComponent())
    }
}

@Preview(name = "light")
@Composable
fun SettingsUiPreviewLight() {
    SettingsUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun SettingsUiPreviewDark() {
    SettingsUiPreview(darkTheme = true)
}