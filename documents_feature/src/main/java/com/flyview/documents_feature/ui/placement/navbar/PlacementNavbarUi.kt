package com.flyview.documents_feature.ui.placement.navbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.flyview.core.R
import com.flyview.core.theme.AppTheme
import com.flyview.core.utils.ICON_SIZE

@Composable
fun PlacementNavbarUi(component: PlacementNavbarComponent) {


    NavigationBar(){
        NavigationBarItem(
            selected = false/*MarkListTab.BOX == currentTab.value*/,
            onClick = {  },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_box),
                    contentDescription = "pack",
                    modifier = Modifier.size(ICON_SIZE)
                )
            },
            label = { Text(text = "Нужен") },
            enabled = true/*MarkListTab.BOX != currentTab.value*/
        )

        NavigationBarItem(
            selected = false/*MarkListTab.BOX == currentTab.value*/,
            onClick = {  },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_box),
                    contentDescription = "box",
                    modifier = Modifier.size(ICON_SIZE)
                )
            },
            label = { Text(text = "Собран") },
            enabled = true/*MarkListTab.BOX != currentTab.value*/
        )
    }
}

@Composable
fun PlacementNavbarUiPreview(darkTheme: Boolean){
    AppTheme (darkTheme = darkTheme) {
        PlacementNavbarUi(FakePlacementNavbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun PlacementNavbarUiPreviewLight() {
    PlacementNavbarUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun PlacementNavbarUiPreviewDark() {
    PlacementNavbarUiPreview(darkTheme = true)
}