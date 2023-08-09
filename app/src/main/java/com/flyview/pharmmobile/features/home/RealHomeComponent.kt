package com.flyview.pharmmobile.features.home

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.features.home.toolbar.RealHomeToolbarComponent

class RealHomeComponent(
    componentContext: ComponentContext,
    private val onBarcodeReaderClick: () -> Unit,
    private val onSettingsClick: () -> Unit,
    private val onInventoryClick: () -> Unit
) : ComponentContext by componentContext, HomeComponent {

    override val toolbarComponent = RealHomeToolbarComponent(
        componentContext = componentContext,
        onBarcodeReaderClick = this.onBarcodeReaderClick,
        onSettingsClick = this.onSettingsClick
    )

    override fun onInventoryClick() = this.onInventoryClick.invoke()
}