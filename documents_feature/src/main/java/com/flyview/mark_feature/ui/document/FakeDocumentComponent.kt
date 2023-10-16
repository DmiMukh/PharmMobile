package com.flyview.mark_feature.ui.document

import com.flyview.mark_feature.ui.document.toolbar.FakeDocumentToolbarComponent

class FakeDocumentComponent : DocumentComponent {
    override val toolbarComponent = FakeDocumentToolbarComponent()
}