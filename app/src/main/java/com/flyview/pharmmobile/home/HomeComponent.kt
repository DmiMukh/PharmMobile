package com.flyview.pharmmobile.home

import com.flyview.pharmmobile.home.toolbar.HomeToolbarComponent

interface HomeComponent {

    val toolbarComponent: HomeToolbarComponent
    fun onDocumentsCLick()
    fun onInventoryClick()
}