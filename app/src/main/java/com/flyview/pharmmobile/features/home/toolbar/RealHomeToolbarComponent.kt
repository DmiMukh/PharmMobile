package com.flyview.pharmmobile.features.home.toolbar

import com.arkivanov.decompose.ComponentContext

class RealHomeToolbarComponent(
    componentContext: ComponentContext,
    private val onBarcodeReaderClick: () -> Unit
) : ComponentContext by componentContext, HomeToolbarComponent {
    override fun onBarcodeReaderClick() = this.onBarcodeReaderClick.invoke()
}