package com.flyview.documents_feature.domain.repository

import com.flyview.core.barcode.data.Barcode
import com.flyview.documents_feature.domain.model.Product

interface MarkRepository {

    suspend fun deleteCode(code: String)
    suspend fun getProductByCode(code: String): Product?
    suspend fun putProductCode(barcode: Barcode)
    suspend fun saveProductCodes(product: Product)
}