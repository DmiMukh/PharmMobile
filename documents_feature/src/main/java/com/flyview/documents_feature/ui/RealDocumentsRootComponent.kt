package com.flyview.documents_feature.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.core.utils.toStateFlow
import com.flyview.documents_feature.DocumentsComponentFactory
import com.flyview.documents_feature.cratePlacementComponent
import com.flyview.documents_feature.createDocumentComponent
import com.flyview.documents_feature.createMainComponent
import com.flyview.documents_feature.createMarkListComponent
import com.flyview.documents_feature.createProductListComponent
import com.flyview.documents_feature.domain.model.Document
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

class RealDocumentsRootComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val componentFactory: DocumentsComponentFactory
) : ComponentContext by componentContext, DocumentsRootComponent {
    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, DocumentsRootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Main,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): DocumentsRootComponent.Child = when (config) {

        is ChildConfig.Details -> DocumentsRootComponent.Child.Details(
            component = this.componentFactory.createDocumentComponent(
                componentContext = componentContext,
                document = config.document,
                onBack = { navigation.pop() }
            )
        )

        is ChildConfig.Main -> DocumentsRootComponent.Child.Main(
            component = this.componentFactory.createMainComponent(
                componentContext = componentContext,
                onBack = this.onBack,
                onItemClick = { document -> navigation.push(ChildConfig.Details(document = document)) }
            )
        )

        is ChildConfig.MarkList -> DocumentsRootComponent.Child.MarkList(
            component = this.componentFactory.createMarkListComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() }
            )
        )

        is ChildConfig.Placement -> DocumentsRootComponent.Child.Placement(
            component = this.componentFactory.cratePlacementComponent(
                componentContext = componentContext,
                onBack = { navigation.pop() }
            )
        )

        is ChildConfig.ProductList -> DocumentsRootComponent.Child.ProductList(
            component = this.componentFactory.createProductListComponent(
                componentContext = componentContext,
                document = config.document,
                onBack = { navigation.pop() }
            )
        )
    }

    sealed interface ChildConfig : Parcelable {
        @Parcelize
        class Details(val document: Document) : ChildConfig

        @Parcelize
        object Main : ChildConfig

        @Parcelize
        class MarkList(val product: Int) : ChildConfig

        @Parcelize
        class Placement(val document: Document) : ChildConfig

        @Parcelize
        class ProductList(val document: Document) : ChildConfig
    }
}