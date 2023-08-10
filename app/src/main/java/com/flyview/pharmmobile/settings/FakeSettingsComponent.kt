package com.flyview.pharmmobile.settings

import com.flyview.pharmmobile.settings.toolbar.FakeSettingsToolbarComponent

class FakeSettingsComponent: SettingsComponent {

    override val toolbarComponent = FakeSettingsToolbarComponent()
}