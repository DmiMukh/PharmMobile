package com.flyview.inventory_feature.ui.main.toolbar

import com.arkivanov.decompose.ComponentContext

class RealMainToolbarComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
): ComponentContext by componentContext, MainToolbarComponent {
    override fun onBackClick() = this.onBack.invoke()
}