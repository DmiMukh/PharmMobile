package com.flyview.pharmmobile.features.settings

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.features.settings.toolbar.RealSettingsToolbarComponent

class RealSettingsComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit
) : ComponentContext by componentContext, SettingsComponent {

    override val toolbarComponent = RealSettingsToolbarComponent(
        componentContext = componentContext,
        onBackClick = this.onBackClick
    )

}