package com.flyview.inventory_feature.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.flyview.core.theme.AppTheme
import com.flyview.inventory_feature.ui.details.item.ProductItem
import com.flyview.inventory_feature.ui.details.toolbar.DocumentDetailsToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentDetailsUi(component: DocumentDetailsComponent) {

    val productsData = component.productsPager.collectAsLazyPagingItems()

    Scaffold(
        topBar = { DocumentDetailsToolbarUi(component.toolbarComponent) })
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row {
                    Row {
                        Text(text = "Записей:", modifier = Modifier.padding(end = 4.dp))
                        Text(text = productsData.itemCount.toString())
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(productsData) { product ->
                    product?.let {
                        ProductItem(
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
fun DocumentDetailsUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        DocumentDetailsUi(FakeDocumentDetailsComponent())
    }
}

@Preview(name = "light")
@Composable
fun DocumentDetailsUiPreviewLight() {
    DocumentDetailsUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun DocumentDetailsUiPreviewDark() {
    DocumentDetailsUiPreview(darkTheme = true)
}