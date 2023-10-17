package com.flyview.documents_feature.ui.main.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainViewNoItems(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(96.dp),
            imageVector = Icons.Filled.Info,
            contentDescription = "No Items Found"
        )

        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
            text = "Нет данных",
            textAlign = TextAlign.Center
        )
    }


}