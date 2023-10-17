package com.flyview.documents_feature.ui.scan.mark_list.toolbar

import com.arkivanov.decompose.ComponentContext

class RealMarkListToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onSave: () -> Unit
) : ComponentContext by componentContext, MarkListToolbarComponent {
    override fun onBackClick() = this.onBack.invoke()

    override fun onSaveClick() = this.onSave.invoke()
}