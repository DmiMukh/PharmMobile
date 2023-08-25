package com.flyview.inventory_feature.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.flyview.core.theme.AppTheme
import com.flyview.inventory_feature.ui.list.dialog.ListDialogState
import com.flyview.inventory_feature.ui.list.dialog.ListDialogUi
import com.flyview.inventory_feature.ui.list.item.DocumentItem
import com.flyview.inventory_feature.ui.list.toolbar.DocumentListToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentListUi(component: DocumentListComponent) {

    val dialogState = component.dialogComponent.state.collectAsState()
    val documentsData = component.documentsPager.collectAsLazyPagingItems()

    if (dialogState.value != ListDialogState.Hidden)
        ListDialogUi(component.dialogComponent)

    Scaffold(
        topBar = { DocumentListToolbarUi(component.toolbarComponent) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = component::onCreateDocumentClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add_document"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            Row {
                Text(text = "Записей:", modifier = Modifier.padding(end = 4.dp))
                Text(text = documentsData.itemCount.toString())
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(documentsData) { document ->
                    document?.let {
                        DocumentItem(
                            model = it,
                            onClick = component::onItemClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DocumentListUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentListUi(FakeDocumentListComponent())
    }
}

@Preview(name = "light")
@Composable
fun DocumentListUiPreviewLight() {
    DocumentListUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentListUiPreviewDark() {
    DocumentListUiPreview(darkTheme = true)
}


