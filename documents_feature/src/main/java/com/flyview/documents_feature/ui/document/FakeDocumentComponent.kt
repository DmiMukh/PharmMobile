package com.flyview.documents_feature.ui.document

import com.flyview.documents_feature.ui.document.toolbar.FakeDocumentToolbarComponent

class FakeDocumentComponent : DocumentComponent {
    override val toolbarComponent = FakeDocumentToolbarComponent()
    override fun onScanClick() = Unit
    override fun onPlacementClick() = Unit
}