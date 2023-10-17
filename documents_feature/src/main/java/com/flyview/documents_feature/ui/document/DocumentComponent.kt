package com.flyview.documents_feature.ui.document

import com.flyview.documents_feature.ui.document.toolbar.DocumentToolbarComponent

interface DocumentComponent {

    val toolbarComponent: DocumentToolbarComponent

    fun onScanClick()
    fun onPlacementClick()
}