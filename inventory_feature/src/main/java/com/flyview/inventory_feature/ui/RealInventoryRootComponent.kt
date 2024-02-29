package com.flyview.inventory_feature.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.core.utils.toStateFlow
import com.flyview.inventory_feature.InventoryComponentFactory
import com.flyview.inventory_feature.createInventoryDetailsComponent
import com.flyview.inventory_feature.createInventoryListComponent
import com.flyview.inventory_feature.createInventoryMainComponent
import com.flyview.inventory_feature.createInventoryEditComponent
import com.flyview.inventory_feature.createInventoryTestCameraComponent
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.model.Product
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

class RealInventoryRootComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val componentFactory: InventoryComponentFactory
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

        is ChildConfig.Details -> InventoryRootComponent.Child.Details(
            component = this.componentFactory.createInventoryDetailsComponent(
                componentContext = componentContext,
                document = config.document,
                onBack = { navigation.pop() },
                onEditProduct = { product ->
                    navigation.push(
                        ChildConfig.Edit(
                            product = product,
                            documentId = config.document.id
                        )
                    )
                }
            )
        )

        is ChildConfig.Edit -> InventoryRootComponent.Child.Edit(
            component = this.componentFactory.createInventoryEditComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() },
                product = config.product,
                documentId = config.documentId
            )
        )

        is ChildConfig.List -> InventoryRootComponent.Child.List(
            component = this.componentFactory.createInventoryListComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() },
                onDocumentDetails = { document ->
                    navigation.push(ChildConfig.Details(document))
                }
            )
        )

        is ChildConfig.Main -> InventoryRootComponent.Child.Main(
            this.componentFactory.createInventoryMainComponent(
                componentContext = componentContext,
                onBack = this.onBack,
                onDocumentsClick = { navigation.push(ChildConfig.List) },
                onTestCameraClick = { navigation.push(ChildConfig.TestCamera) }
            )
        )

        is ChildConfig.TestCamera -> InventoryRootComponent.Child.TestCamera(
            this.componentFactory.createInventoryTestCameraComponent(
                componentContext = componentContext,
                onBack = this.onBack
            )
        )
    }

    sealed interface ChildConfig : Parcelable {

        @Parcelize
        class Details(val document: Document) : ChildConfig

        @Parcelize
        class Edit(val product: Product, val documentId: Long) : ChildConfig

        @Parcelize
        object List : ChildConfig

        @Parcelize
        object Main : ChildConfig

        @Parcelize
        object TestCamera : ChildConfig
    }
}