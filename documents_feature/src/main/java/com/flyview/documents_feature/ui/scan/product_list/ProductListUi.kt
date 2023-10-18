package com.flyview.documents_feature.ui.scan.product_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.flyview.core.theme.AppTheme
import com.flyview.documents_feature.ui.scan.product_list.item.ProductItemUi
import com.flyview.documents_feature.ui.scan.product_list.toolbar.ProductListToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListUi(component: ProductListComponent) {

    val productsData = component.productsPager.collectAsLazyPagingItems()

    Scaffold(
        topBar = { ProductListToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(productsData) { product ->
                    product?.let {
                        ProductItemUi(
                            model = product,
                            onClick = component::onItemClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductListUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        ProductListUi(FakeProductListComponent())
    }
}

@Preview(name = "light")
@Composable
fun ProductListUiPreviewLight() = ProductListUiPreview(darkTheme = false)

@Preview(name = "dark")
@Composable
fun ProductListUiPreviewDark() = ProductListUiPreview(darkTheme = false)
