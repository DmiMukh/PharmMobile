package com.flyview.mark_feature

import com.arkivanov.decompose.ComponentContext
import com.flyview.mark_feature.ui.main.MainComponent
import com.flyview.mark_feature.ui.main.RealMainComponent
import org.koin.dsl.module

val markModule = module {

}

fun MarkComponentFactory.createMarkMainComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): MainComponent {
    return RealMainComponent(
        componentContext = componentContext,
        onBack = onBack
    )
}