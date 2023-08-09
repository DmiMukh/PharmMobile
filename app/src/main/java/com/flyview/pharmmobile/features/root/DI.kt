package com.flyview.pharmmobile.features.root

import com.arkivanov.decompose.ComponentContext
import com.flyview.pharmmobile.core.ComponentFactory
import com.flyview.pharmmobile.features.root.ui.RealRootComponent
import com.flyview.pharmmobile.features.root.ui.RootComponent
import org.koin.core.component.get

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(
        componentContext = componentContext,
        componentFactory = get()
    )
}