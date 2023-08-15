package com.flyview.inventory_feature.ui.product_edit

import kotlinx.coroutines.flow.StateFlow

interface ProductEditComponent {

    val articul: String
    val certificate: String
    val divQuantity: StateFlow<String>
    val isDivisibility: Boolean
    val modQuantity: StateFlow<String>
    val producer: String

    fun onCancelClick()
    fun onChangeDivQuantity(newValue: String)
    fun onChangeModQuantity(newValue: String)

    fun onSaveClick()
}