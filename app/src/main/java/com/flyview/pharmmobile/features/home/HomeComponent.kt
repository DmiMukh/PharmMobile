package com.flyview.pharmmobile.features.home

import com.flyview.pharmmobile.features.home.toolbar.HomeToolbarComponent

interface HomeComponent {

    val toolbarComponent: HomeToolbarComponent
    fun onInventoryClick()
}