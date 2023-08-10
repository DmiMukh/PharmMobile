package com.flyview.pharmmobile.root

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.pharmmobile.root.ui.RealRootComponent
import com.flyview.pharmmobile.root.ui.RootComponent
import org.koin.core.component.get

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(
        componentContext = componentContext,
        componentFactory = get()
    )
}