package com.flyview.inventory_feature.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.ui.list.FakeDocumentListComponent
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class RealInventoryRepository: InventoryRepository {
    override fun documentsPager(): Flow<PagingData<Document>> =
        Pager(PagingConfig(pageSize = 10)) { FakeDocumentListComponent.FakePagingSource() }.flow

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