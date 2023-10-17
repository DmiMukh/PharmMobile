package com.flyview.documents_feature.ui.placement.navbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pack),
                    contentDescription = "mark",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pack),
                    contentDescription = "mark",
                    modifier = Modifier.size(ICON_SIZE)
                )
            }
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