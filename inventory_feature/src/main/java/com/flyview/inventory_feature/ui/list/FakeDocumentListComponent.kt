package com.flyview.inventory_feature.ui.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.ui.list.dialog.FakeListDialogComponent
import com.flyview.inventory_feature.ui.list.toolbar.FakeDocumentListToolbarComponent
import java.io.IOException

class FakeDocumentListComponent : DocumentListComponent {

    override val dialogComponent = FakeListDialogComponent()
    override val documentsPager = Pager(PagingConfig(pageSize = 10)) { FakePagingSource() }.flow

    override val toolbarComponent = FakeDocumentListToolbarComponent()
    override fun onBackClick() = Unit
    override fun onCreateDocumentClick() = Unit
    override fun onItemClick(document: Document) = Unit

    internal class FakePagingSource : PagingSource<Int, Document>() {

        override fun getRefreshKey(state: PagingState<Int, Document>) = null

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
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