package com.flyview.documents_feature.ui.main.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flyview.documents_feature.ui.main.MainViewState

@Composable
fun MainViewError(
    state: MainViewState.Error,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(96.dp),
            imageVector = Icons.Filled.Warning,
            contentDescription = "Error loading items"
        )

        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
            text = state.msg,
            textAlign = TextAlign.Center
        )
    }
}