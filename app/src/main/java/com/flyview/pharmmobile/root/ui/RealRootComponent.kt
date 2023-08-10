package com.flyview.pharmmobile.root.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.core.ComponentFactory
import com.flyview.core.utils.toStateFlow
import com.flyview.inventory_feature.createInventoryComponent
import com.flyview.pharmmobile.createHomeComponent
import com.flyview.pharmmobile.createSettingsComponent
import com.flyview.pharmmobile.createSplashComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Splash,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {

        ChildConfig.Home -> RootComponent.Child.Home(
            component = this.componentFactory.createHomeComponent(
                componentContext = componentContext,
                onBarcodeReaderClick = { TODO("Добавить нафигация на экран USB-устройств") },
                onSettingsClick = { navigation.push(ChildConfig.Settings) },
                onInventoryClick = { navigation.push(ChildConfig.InventoryRoot) }
            )
        )

        ChildConfig.InventoryRoot -> RootComponent.Child.InventoryRoot(
            component = this.componentFactory.createInventoryComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() },
                componentFactory = componentFactory
            )
        )

        ChildConfig.Settings -> RootComponent.Child.Settings(
            component = this.componentFactory.createSettingsComponent(
                componentContext = componentContext,
                onBackClick = { navigation.pop() }
            )
        )

        ChildConfig.Splash -> RootComponent.Child.Splash(
            component = this.componentFactory.createSplashComponent(
                componentContext = componentContext,
                onFinish = { navigation.replaceCurrent(ChildConfig.Home) }
            )
        )
    }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object Home : ChildConfig

        @Parcelize
        object InventoryRoot : ChildConfig

        @Parcelize
        object Settings : ChildConfig

        @Parcelize
        object Splash : ChildConfig
    }
}