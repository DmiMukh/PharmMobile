package com.flyview.inventory_feature.ui.product_edit

import com.flyview.inventory_feature.domain.model.Articul
import com.flyview.inventory_feature.domain.model.Certificate
import kotlinx.coroutines.flow.StateFlow

interface ProductEditComponent {

    val articul: Articul
    val certificate: Certificate
    val divQuantity: StateFlow<String>
    val isDivisibility: Boolean
    val modQuantity: StateFlow<String>


    fun onCancelClick()
    fun onChangeDivQuantity(newValue: String)
    fun onChangeModQuantity(newValue: String)

    fun onSaveClick()
}