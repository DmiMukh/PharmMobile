package com.flyview.pharmmobile.settings

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.settings.toolbar.RealSettingsToolbarComponent

class RealSettingsComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit
) : ComponentContext by componentContext, SettingsComponent {

    override val toolbarComponent = RealSettingsToolbarComponent(
        componentContext = componentContext,
        onBackClick = this.onBackClick
    )

}