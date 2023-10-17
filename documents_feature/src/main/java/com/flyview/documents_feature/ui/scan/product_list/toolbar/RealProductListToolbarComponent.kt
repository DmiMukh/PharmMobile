package com.flyview.documents_feature.ui.scan.product_list.toolbar

import com.arkivanov.decompose.ComponentContext

class RealProductListToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onSave: () -> Unit
) : ComponentContext by componentContext, ProductListToolbarComponent {
    override fun onBackClick() = this.onBack.invoke()
    override fun onSaveClick() = this.onSave.invoke()
}