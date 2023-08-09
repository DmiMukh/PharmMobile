package com.flyview.pharmmobile.features.home

import com.arkivanov.decompose.ComponentContext

class RealHomeComponent(
    componentContext: ComponentContext,
    private val onInventoryClick: () -> Unit
) : ComponentContext by componentContext, HomeComponent {

    override fun onInventoryClick() = this.onInventoryClick.invoke()
}