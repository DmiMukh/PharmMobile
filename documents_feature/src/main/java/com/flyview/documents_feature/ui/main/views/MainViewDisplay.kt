package com.flyview.documents_feature.ui.main.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.main.MainViewState
import com.flyview.documents_feature.ui.main.item.MainItemUi

@Composable
fun MainViewDisplay(
    viewState: MainViewState.Display,
    onClick: (Document) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        viewState.items.forEach { model ->
            item {
                MainItemUi(
                    model = model,
                    onClick = onClick
                )
            }
        }
    }
}