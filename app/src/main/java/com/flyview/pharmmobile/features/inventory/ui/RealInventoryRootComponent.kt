package com.flyview.pharmmobile.features.inventory.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.pharmmobile.core.ComponentFactory
import com.flyview.pharmmobile.core.utils.toStateFlow
import com.flyview.pharmmobile.features.inventory.createInventoryFeatureDocumentListComponent
import com.flyview.pharmmobile.features.inventory.createInventoryFeatureMainComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

class RealInventoryRootComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit,
    private val componentFactory: ComponentFactory
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

        ChildConfig.List -> InventoryRootComponent.Child.List(
            component = componentFactory.createInventoryFeatureDocumentListComponent(
                componentContext = componentContext,
                onBackClick = { navigation.pop() }
            )
        )

        ChildConfig.Main -> InventoryRootComponent.Child.Main(
            component = componentFactory.createInventoryFeatureMainComponent(
                componentContext = componentContext,
                onBackClick = this.onBackClick,
                onDocumentsClick = {
                    navigation.push(ChildConfig.List)
                }
            )
        )
    }

    sealed interface ChildConfig : Parcelable {

        @Parcelize
        object List : ChildConfig

        @Parcelize
        object Main : ChildConfig
    }
}