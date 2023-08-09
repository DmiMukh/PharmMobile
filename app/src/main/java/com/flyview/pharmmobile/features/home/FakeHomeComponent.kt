package com.flyview.pharmmobile.features.home

import com.flyview.pharmmobile.features.home.toolbar.FakeHomeToolbarComponent

class FakeHomeComponent : HomeComponent {

    override val toolbarComponent = FakeHomeToolbarComponent()

    override fun onInventoryClick() = Unit
}