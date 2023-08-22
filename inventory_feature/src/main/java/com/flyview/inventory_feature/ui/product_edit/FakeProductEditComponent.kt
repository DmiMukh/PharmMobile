package com.flyview.inventory_feature.ui.product_edit

import com.flyview.inventory_feature.domain.model.Articul
import com.flyview.inventory_feature.domain.model.Certificate
import kotlinx.coroutines.flow.MutableStateFlow

class FakeProductEditComponent: ProductEditComponent {
    override val articul = Articul()
    override val certificate = Certificate()
    override val divQuantity = MutableStateFlow("")
    override val isDivisibility: Boolean = true
    override val modQuantity = MutableStateFlow("")

    override fun onCancelClick() = Unit
    override fun onChangeDivQuantity(newValue: String) = Unit
    override fun onChangeModQuantity(newValue: String) = Unit
    override fun onSaveClick() = Unit
}