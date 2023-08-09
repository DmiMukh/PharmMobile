package com.flyview.pharmmobile.features.settings

import com.flyview.pharmmobile.features.settings.toolbar.FakeSettingsToolbarComponent

class FakeSettingsComponent: SettingsComponent {

    override val toolbarComponent = FakeSettingsToolbarComponent()
}