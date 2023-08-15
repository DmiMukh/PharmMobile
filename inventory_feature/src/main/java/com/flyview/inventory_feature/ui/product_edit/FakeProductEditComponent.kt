package com.flyview.inventory_feature.ui.product_edit

import kotlinx.coroutines.flow.MutableStateFlow

class FakeProductEditComponent: ProductEditComponent {
    override val articul = "Аспирин Кардио 500 мг №10"
    override val producer = "ПАО Обновление"
    override val certificate = "123456&1"
    override val divQuantity = MutableStateFlow("")
    override val isDivisibility: Boolean = false
    override val modQuantity = MutableStateFlow("")

    override fun onCancelClick() = Unit

    override fun onChangeDivQuantity(newValue: String) = Unit

    override fun onChangeModQuantity(newValue: String) = Unit

    override fun onSaveClick() = Unit
}