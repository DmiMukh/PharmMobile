package com.flyview.mark_feature.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.flyview.core.utils.toStateFlow
import com.flyview.mark_feature.MarkComponentFactory
import com.flyview.mark_feature.createMarkMainComponent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

class RealMarkRootComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val componentFactory: MarkComponentFactory
) : ComponentContext by componentContext, MarkRootComponent {
    private val navigation = StackNavigation<ChildConfig>()


    override val childStack: StateFlow<ChildStack<*, MarkRootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Main,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): MarkRootComponent.Child = when (config) {
        is ChildConfig.Main -> MarkRootComponent.Child.Main(
            component = this.componentFactory.createMarkMainComponent(
                componentContext = componentContext,
                onBack = this.onBack
            )
        )
    }

    sealed interface ChildConfig : Parcelable {
        @Parcelize
        object Main : ChildConfig
    }
}