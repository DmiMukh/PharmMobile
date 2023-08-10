package com.flyview.pharmmobile.features.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.flyview.inventory_feature.ui.InventoryRootComponent
import com.flyview.pharmmobile.features.home.HomeComponent
import com.flyview.pharmmobile.features.settings.SettingsComponent
import com.flyview.pharmmobile.features.splash.SplashComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {


        class Home(val component: HomeComponent) : Child
        class InventoryRoot(val component: InventoryRootComponent) : Child
        class Settings(val component: SettingsComponent) : Child
        class Splash(val component: SplashComponent) : Child
    }
}