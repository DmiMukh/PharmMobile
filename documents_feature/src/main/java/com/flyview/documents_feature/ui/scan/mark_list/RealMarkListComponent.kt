package com.flyview.documents_feature.ui.scan.mark_list

import com.arkivanov.decompose.ComponentContext
import com.flyview.documents_feature.ui.scan.mark_list.toolbar.RealMarkListToolbarComponent

class RealMarkListComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, MarkListComponent {
    override val toolbarComponent = RealMarkListToolbarComponent(
        componentContext = componentContext
    )
}