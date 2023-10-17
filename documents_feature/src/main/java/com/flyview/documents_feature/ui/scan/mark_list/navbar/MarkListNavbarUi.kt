package com.flyview.documents_feature.ui.scan.mark_list.navbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.R
import com.flyview.core.theme.AppTheme

@Composable
fun MarkListNavbarUi(component: MarkListNavbarComponent){

    val currentTab = component.viewState.collectAsState()

    NavigationBar {
        NavigationBarItem(
            selected = MarkListTab.PACK == currentTab.value,
            onClick = {  },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pack),
                    contentDescription = "pack",
                    modifier = Modifier.size(28.dp)
                )
            },
            label = { Text(text = "Упаковка") },
            enabled = MarkListTab.PACK != currentTab.value
        )

        NavigationBarItem(
            selected = MarkListTab.BOX == currentTab.value,
            onClick = {  },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_box),
                    contentDescription = "box",
                    modifier = Modifier.size(28.dp)
                )
            },
            label = { Text(text = "Короб") },
            enabled = MarkListTab.BOX != currentTab.value
        )
    }
}

@Composable
fun MarkListNavbarUiPreview(darkTheme: Boolean) {
    AppTheme (darkTheme = darkTheme) {
        MarkListNavbarUi(FakeMarkListNavbarComponent())
    }
}

@Preview(name = "light")
@Composable
fun MarkListNavbarUiPreviewLight() = MarkListNavbarUiPreview(darkTheme = false)

@Preview(name = "dark")
@Composable
fun MarkListNavbarUiPreviewDark() = MarkListNavbarUiPreview(darkTheme = true)
