package com.flyview.documents_feature.data.repository

import com.flyview.core.barcode.data.Barcode
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.domain.repository.MarkRepository

class MarkRepositoryImpl: MarkRepository {
    override suspend fun deleteCode(code: String) {
        TODO("Not yet implemented deleteCode")
    }

    override suspend fun getProductByCode(code: String): Product? {
        TODO("Not yet implemented getProductByCode")
    }

    override suspend fun putProductCode(barcode: Barcode) {
        TODO("Not yet implemented putProductCode")
    }

    override suspend fun saveProductCodes(product: Product) {
        TODO("Not yet implemented saveProductCodes")
    }
}