package com.flyview.pharmmobile.root.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.core.ComponentFactory
import com.flyview.core.createMessageComponent
import com.flyview.core.utils.toStateFlow
import com.flyview.documents_feature.createDocumentsComponent
import com.flyview.inventory_feature.createInventoryComponent
import com.flyview.pharmmobile.createHomeComponent
import com.flyview.pharmmobile.createSettingsComponent
import com.flyview.pharmmobile.createSplashComponent
import com.flyview.pharmmobile.createUsbListComponent
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

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {

        ChildConfig.DocumentsRoot -> RootComponent.Child.DocumentsRoot(
            component = this.componentFactory.createDocumentsComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() }
            )
        )

        ChildConfig.Home -> RootComponent.Child.Home(
            component = this.componentFactory.createHomeComponent(
                componentContext = componentContext,
                onBarcodeReaderClick = { navigation.push(ChildConfig.UsbList) },
                onSettingsClick = { navigation.push(ChildConfig.Settings) },
                onDocumentsCLick = { navigation.push(ChildConfig.DocumentsRoot) },
                onInventoryClick = { navigation.push(ChildConfig.InventoryRoot) }
            )
        )

        ChildConfig.InventoryRoot -> RootComponent.Child.InventoryRoot(
            component = this.componentFactory.createInventoryComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() }
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

        ChildConfig.UsbList -> RootComponent.Child.UsbList(
            component = this.componentFactory.createUsbListComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() }
            )
        )
    }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object DocumentsRoot : ChildConfig
        @Parcelize
        object Home : ChildConfig

        @Parcelize
        object InventoryRoot : ChildConfig

        @Parcelize
        object Settings : ChildConfig

        @Parcelize
        object Splash : ChildConfig

        @Parcelize
        object UsbList : ChildConfig
    }
}