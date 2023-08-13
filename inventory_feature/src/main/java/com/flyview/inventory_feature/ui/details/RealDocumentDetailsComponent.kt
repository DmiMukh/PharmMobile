package com.flyview.inventory_feature.ui.details

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.domain.barcode.BarcodeReader
import com.flyview.core.domain.barcode.BarcodeReaderData
import com.flyview.core.utils.GS1
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
            13 -> {
                val regex = Regex("[0-9]{13}")

                if (regex.matches(code)) {

                    val product = repository.getProduct(code)

                }
            }
            85 -> {
                val regex = Regex("01".plus("[0-9]{14}").plus("21").plus("\\S{13}")
                        .plus(GS1).plus("91").plus("\\S{4}").plus(GS1).plus("92")
                        .plus("\\S{44}")
                )

                if (regex.matches(code)) {
                    val product = repository.getProduct(code)
                }
            }
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

    }

    override fun onItemClick(product: Product) {
        BarcodeReaderData.data.onEach {
            if (it.isNotEmpty()) onReadBarcode(it)
        }.launchIn(coroutineScope)
    }
}