package com.flyview.inventory_feature.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.Product
import java.io.IOException

class RealInventoryRepository : InventoryRepository {
    override suspend fun addProduct(product: Product, documentId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun createDocument(): Document {
        return Document()
        TODO("Добавить создание документа")
    }

    override suspend fun getProduct(code: String): Product {
        TODO("Not yet implemented")
    }

    override fun getDocumentsPager() =
        Pager(PagingConfig(pageSize = 10)) { FakeDocumentPagingSource() }.flow

    override fun getProductsPager(documentId: Long) =
        Pager(PagingConfig(pageSize = 10)) { FakeProductPagingSource() }.flow

    internal class FakeDocumentPagingSource : PagingSource<Int, Document>() {

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

    internal class FakeProductPagingSource : PagingSource<Int, Product>() {

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