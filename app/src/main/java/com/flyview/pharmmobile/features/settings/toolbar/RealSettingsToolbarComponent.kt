package com.flyview.pharmmobile.features.settings.toolbar

import com.arkivanov.decompose.ComponentContext

class RealSettingsToolbarComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit
): SettingsToolbarComponent {
    override fun onBackClick() = this.onBackClick.invoke()
}