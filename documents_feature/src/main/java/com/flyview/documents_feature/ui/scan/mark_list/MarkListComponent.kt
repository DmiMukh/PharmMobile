package com.flyview.documents_feature.ui.scan.mark_list

import com.flyview.documents_feature.domain.model.MarkCode
import com.flyview.documents_feature.ui.scan.mark_list.navbar.MarkListNavbarComponent
import com.flyview.documents_feature.ui.scan.mark_list.toolbar.MarkListToolbarComponent

interface MarkListComponent {

    val navbarComponent: MarkListNavbarComponent
    val toolbarComponent: MarkListToolbarComponent

    fun onItemClick(code: MarkCode)
}