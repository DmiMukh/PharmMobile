package com.flyview.mark_feature.ui.placement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.mark_feature.ui.placement.toolbar.PlacementToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacementUi(component: PlacementComponent) {

    val cell = component.cell.collectAsState()

    Scaffold(
        topBar = { PlacementToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row {
                Text(text = "Текущая ячейка:")
                Text(
                    text = cell.value.name,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

        }
    }
}

@Composable
fun PlacementUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        PlacementUi(FakePlacementComponent())
    }
}

@Preview(name = "light")
@Composable
fun PlacementUiPreviewLight() {
    PlacementUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun PlacementUiPreviewDark() {
    PlacementUiPreview(darkTheme = true)
}