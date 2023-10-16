package com.flyview.mark_feature.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.theme.AppTheme
import com.flyview.mark_feature.ui.main.toolbar.MainToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(component: MainComponent) {

    val state = component.viewState.collectAsState()

    Scaffold(
        topBar = { MainToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            when (state.value) {
                is MainState.Display -> {  }
                is MainState.Error -> {  }
                is MainState.Idle -> {  }
                is MainState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )

                is MainState.NoItems -> {  }
            }
        }
    }
}

@Composable
fun MainUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        MainUi(FakeMainComponent())
    }
}

@Preview(name = "light")
@Composable
fun MainUiPreviewLight() {
    MainUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun MainUiPreviewDark() {
    MainUiPreview(darkTheme = true)
}