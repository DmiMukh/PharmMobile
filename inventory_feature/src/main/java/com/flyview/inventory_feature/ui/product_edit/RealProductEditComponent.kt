package com.flyview.inventory_feature.ui.product_edit

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class RealProductEditComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val product: Product,
    private val documentId: Long,
    private val repository: InventoryRepository
) : ComponentContext by componentContext, ProductEditComponent {

    private val coroutineScope = componentCoroutineScope()
    override val articul: String
        get() = this.product.articul.name
    override val producer: String
        get() = this.product.articul.producer
    override val certificate: String
        get() = this.product.certificate.name
    override val divQuantity = MutableStateFlow("")
    override val isDivisibility = product.articul.divisibility > 1
    override val modQuantity = MutableStateFlow("")
    override fun onCancelClick() = this.onBack.invoke()

    override fun onChangeDivQuantity(newValue: String) {
        divQuantity.value = newValue
    }

    override fun onChangeModQuantity(newValue: String) {
        modQuantity.value = newValue
    }

    override fun onSaveClick() {
        coroutineScope.launch {
            val regex = Regex("[0-9]+")

            val divQty = if (regex.matches(divQuantity.value)) divQuantity.value.toBigDecimal()
            else BigDecimal(0)

            val modQty = if (regex.matches(modQuantity.value))
                modQuantity.value.toBigDecimal() / product.articul.divisibility.toBigDecimal()
            else BigDecimal(0)

            val newQuantity = divQty.plus(modQty)

            if (newQuantity > BigDecimal(0)) {
                repository.upsertProduct(
                    product = product,
                    documentId = documentId,
                    newQuantity = newQuantity.toDouble()
                )
            } else {
                repository.deleteProduct(
                    product = product,
                    documentId = documentId
                )
            }
        }
    }
}