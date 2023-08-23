package com.flyview.inventory_feature.ui.list

import androidx.paging.PagingData
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.ui.list.toolbar.DocumentListToolbarComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface DocumentListComponent {
    val documentsPager: Flow<PagingData<Document>>

    val toolbarComponent: DocumentListToolbarComponent

    fun onBackClick()
    fun onCreateDocumentClick()
    fun onItemClick(document: Document)
}