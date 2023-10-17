package com.flyview.documents_feature.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.flyview.documents_feature.ui.main.toolbar.MainToolbarUi
import com.flyview.documents_feature.ui.main.views.MainViewDisplay
import com.flyview.documents_feature.ui.main.views.MainViewError
import com.flyview.documents_feature.ui.main.views.MainViewNoItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(component: MainComponent) {

    val viewState = component.viewState.collectAsState()

    Scaffold(
        topBar = { MainToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val state = viewState.value) {
                is MainViewState.Display -> {
                    MainViewDisplay(
                        viewState = state,
                        onClick = component::onItemClick
                    )
                }

                is MainViewState.Error -> {
                    MainViewError(
                        state = state,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is MainViewState.Idle -> {}
                is MainViewState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is MainViewState.NoItems -> {
                    MainViewNoItems(modifier = Modifier.align(Alignment.Center))
                }
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