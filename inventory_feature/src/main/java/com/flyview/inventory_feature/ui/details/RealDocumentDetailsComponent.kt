package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.domain.barcode.BarcodeReader
import com.flyview.core.domain.barcode.BarcodeReaderData
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.Product
import com.flyview.inventory_feature.ui.details.toolbar.RealDocumentDetailsToolbarComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealDocumentDetailsComponent(
    componentContext: ComponentContext,
    private val document: Document,
    private val onBack: () -> Unit,
    private val repository: InventoryRepository,
    private val barcodeReader: BarcodeReader
) : ComponentContext by componentContext, DocumentDetailsComponent {

    private val coroutineScope = componentCoroutineScope()

    override val productsPager = this.repository.getProductsPager(this.document.id)

    override val toolbarComponent = RealDocumentDetailsToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        barcodeReader = this.barcodeReader
    )

    private fun onReadBarcode(code: String) = coroutineScope.launch {
        when (code.length) {
            13 -> TODO("Добавить обработку кода с длиной 13")
            85 -> TODO("Добавить обработку кода с длиной 85")
            else -> TODO("Добавить отображение ошибки некорректного кода")
        }

        TODO("Проверяем, добавлена ли позиция в документ")
        TODO("Ищем товар по коду")

        TODO("Если товар не найден, то выдаем сообщение")

        TODO("Обработка увеличения кол-ва по обычному коду")
        TODO("1. Если товар есть в документе, то увеличиваем кол-во на 1")
        TODO("2. Если товара нет в документе, то добавляем и ставим количество 1 или меньше")

        TODO("Обработка добавления новой позиции")
        TODO("1. Если товар есть в документе, то выдаем ошибку")
        TODO("2. Если товара нет в документе, то добавляем и ставим количество 1 или меньше")

        val product = repository.getProduct(code)
    }

    override fun onItemClick(product: Product) {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(coroutineScope)
    }
}