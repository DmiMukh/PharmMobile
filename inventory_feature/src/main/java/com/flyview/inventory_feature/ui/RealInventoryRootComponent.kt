package com.flyview.inventory_feature.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.core.utils.toStateFlow
import com.flyview.inventory_feature.ui.main.RealMainComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

class RealInventoryRootComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ComponentContext by componentContext, InventoryRootComponent {
    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, InventoryRootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Main,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): InventoryRootComponent.Child = when (config) {
        ChildConfig.Main -> InventoryRootComponent.Child.Main(
            component = RealMainComponent(
                componentContext = componentContext
            )
        )
    }
    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object Main : ChildConfig
    }
}