package com.flyview.documents_feature.ui.scan.mark_list

import com.flyview.documents_feature.domain.model.MarkCode
import com.flyview.documents_feature.ui.scan.mark_list.navbar.FakeMarkListNavbarComponent
import com.flyview.documents_feature.ui.scan.mark_list.toolbar.FakeMarkListToolbarComponent

class FakeMarkListComponent : MarkListComponent {
    override val navbarComponent = FakeMarkListNavbarComponent()
    override val toolbarComponent = FakeMarkListToolbarComponent()
    override fun onItemClick(code: MarkCode) = Unit
}