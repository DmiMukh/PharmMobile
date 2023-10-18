package com.flyview.documents_feature.ui.scan.product_list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.ui.scan.product_list.toolbar.FakeProductListToolbarComponent
import java.io.IOException

class FakeProductListComponent : ProductListComponent {
    override val productsPager = Pager(PagingConfig(pageSize = 10)) { FakePagingSource() }.flow
    override val toolbarComponent = FakeProductListToolbarComponent()
    override fun onItemClick(model: Product) = Unit

    internal class FakePagingSource : PagingSource<Int, Product>() {

        override fun getRefreshKey(state: PagingState<Int, Product>) = null

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
            return try {
                val pageNumber = params.key ?: 0
                val prevKey = if (pageNumber > 0) pageNumber - 1 else null
                val nextKey = null

                LoadResult.Page(
                    data = emptyList(),
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            }
        }
    }
}