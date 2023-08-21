package com.flyview.inventory_feature.ui.product_edit

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.model.Articul
import com.flyview.inventory_feature.domain.model.Certificate
import com.flyview.inventory_feature.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class RealProductEditComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val product: Product,
    private val documentId: Long,
    private val repository: InventoryRepository
) : ComponentContext by componentContext, ProductEditComponent {

    private val coroutineScope = componentCoroutineScope()
    override val articul: Articul
        get() = this.product.articul
    override val certificate: Certificate
        get() = this.product.certificate

    override val divQuantity = MutableStateFlow(
        product.quantity.toInt().toString()
    )
    override val isDivisibility = product.articul.divisibility > 1
    override val modQuantity = MutableStateFlow(
        (product.quantity.mod(1.0) * product.articul.divisibility).toInt().toString()
    )

    override fun onCancelClick() = this.onBack.invoke()

    override fun onChangeDivQuantity(newValue: String) {
        divQuantity.value = newValue
    }

    override fun onChangeModQuantity(newValue: String) {

        if (newValue.isNotEmpty()) {
            val newInt = newValue.toIntOrNull()
            if (newInt == null) return

            if (newInt >= product.articul.divisibility) {
                modQuantity.value = (product.articul.divisibility - 1).toString()
                return
            }
        }

        modQuantity.value = newValue
    }

    private fun getDivQuantity(): BigDecimal {
        divQuantity.value.toBigDecimalOrNull()?.let {
            return it
        }
        return BigDecimal(0)
    }

    private fun getModQuantity(): BigDecimal {
        modQuantity.value.toDoubleOrNull()?.let {
            it.toBigDecimal().let {
                if (it == BigDecimal(0)) return it
                val div = product.articul.divisibility.toBigDecimal()
                return it.divide(div, RoundingMode.HALF_EVEN)
            }
        }
        return BigDecimal(0)
    }

    override fun onSaveClick() {
        coroutineScope.launch {
            val qtyDiv = getDivQuantity()
            val qtyMod = getModQuantity()
            val newQuantity = qtyDiv.plus(qtyMod)

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

            onBack.invoke()
        }
    }
}