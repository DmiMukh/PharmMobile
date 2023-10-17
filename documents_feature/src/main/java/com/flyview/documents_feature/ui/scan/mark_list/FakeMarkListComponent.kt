package com.flyview.documents_feature.ui.scan.mark_list

import com.flyview.documents_feature.ui.scan.mark_list.toolbar.FakeMarkListToolbarComponent

class FakeMarkListComponent : MarkListComponent {
    override val toolbarComponent = FakeMarkListToolbarComponent()
}