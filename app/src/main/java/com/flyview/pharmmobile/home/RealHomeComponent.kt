package com.flyview.pharmmobile.home

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.home.toolbar.RealHomeToolbarComponent

class RealHomeComponent(
    componentContext: ComponentContext,
    private val onBarcodeReaderClick: () -> Unit,
    private val onSettingsClick: () -> Unit,
    private val onDocumentsCLick: () -> Unit,
    private val onInventoryClick: () -> Unit
) : ComponentContext by componentContext, HomeComponent {

    override val toolbarComponent = RealHomeToolbarComponent(
        componentContext = componentContext,
        onBarcodeReaderClick = this.onBarcodeReaderClick,
        onSettingsClick = this.onSettingsClick
    )

    override fun onDocumentsCLick() = this.onDocumentsCLick.invoke()

    override fun onInventoryClick() = this.onInventoryClick.invoke()
}