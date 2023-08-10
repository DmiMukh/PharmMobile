package com.flyview.inventory_feature.ui.details

import com.flyview.inventory_feature.ui.details.toolbar.FakeDocumentDetailsToolbarComponent

class FakeDocumentDetailsComponent : DocumentDetailsComponent {
    override val toolbarComponent = FakeDocumentDetailsToolbarComponent()
}