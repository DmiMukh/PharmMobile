package com.flyview.documents_feature.ui.main

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.message.data.MessageService
import com.flyview.core.utils.componentScope
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.repository.DocumentsRepository
import com.flyview.documents_feature.ui.main.toolbar.RealMainToolbarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class RealMainComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onItemClick: (Document) -> Unit,
    private val repository: DocumentsRepository,
    private val messageService: MessageService
) : ComponentContext by componentContext, MainComponent {

    override val viewState: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState.Idle)
    override val toolbarComponent = RealMainToolbarComponent(
        componentContext = componentContext,
        onBack = this.onBack,
        onRefresh = ::fetchDocuments
    )

    override fun onItemClick(item: Document) = this.onItemClick.invoke(item)

    private fun fetchDocuments(date: LocalDate) {

        if (MainViewState.Loading == viewState.value) return
        viewState.value = MainViewState.Loading

        componentScope.launch {

            val items: List<Document>

            try {
                items = repository.getDocuments(date)
            } catch (ex: Exception) {
                viewState.value = MainViewState.Error(ex.localizedMessage.orEmpty())
                return@launch
            }
            if (items.isEmpty()) {
                viewState.value = MainViewState.NoItems
                return@launch
            }

            viewState.value = MainViewState.Display(items)
            return@launch
        }
    }
}