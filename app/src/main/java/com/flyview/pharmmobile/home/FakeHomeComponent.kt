package com.flyview.pharmmobile.home

import com.flyview.pharmmobile.home.toolbar.FakeHomeToolbarComponent

class FakeHomeComponent : HomeComponent {

    override val toolbarComponent = FakeHomeToolbarComponent()
    override fun onDocumentsCLick() = Unit

    override fun onInventoryClick() = Unit
}