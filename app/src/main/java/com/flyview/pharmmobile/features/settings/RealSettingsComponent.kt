package com.flyview.pharmmobile.features.settings

import com.arkivanov.decompose.ComponentContext

class RealSettingsComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit
) : ComponentContext by componentContext, SettingsComponent {

}