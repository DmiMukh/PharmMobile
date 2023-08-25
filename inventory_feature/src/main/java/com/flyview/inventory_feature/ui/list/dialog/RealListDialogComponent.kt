package com.flyview.inventory_feature.ui.list.dialog

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.core.utils.componentScope
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.model.Document
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealListDialogComponent(
    componentContext: ComponentContext,
    private val repository: InventoryRepository
) : ComponentContext by componentContext, ListDialogComponent {

    private val retainedInstance = instanceKeeper.getOrCreate(::DialogRetainedInstance)

    override val canClose: StateFlow<Boolean>
        get() = retainedInstance.canClose
    override val sendedCount: StateFlow<Int>
        get() = retainedInstance.sendedCount

    override val state = retainedInstance.state
    override val totalCount: StateFlow<Int>
        get() = retainedInstance.documentCount

    override fun onCancelClick() {
        TODO("Not yet implemented")
    }

    override fun onCloseClick() {
        retainedInstance.state.value = ListDialogState.Hidden
    }

    override fun onDismissClick() {
        if (retainedInstance.canClose.value) {
            retainedInstance.state.value = ListDialogState.Hidden
        }
    }

    private fun startSendDocuments() {
        componentScope.launch {
            val documents = repository.getDocuments().filter { !it.sended }

            Log.d("TEST_LIST", documents.size.toString())

            retainedInstance.sendedCount.value = 0
            retainedInstance.documentCount.value = documents.size

            documents.forEach {
                try {
                    sendDocument(it)
                } catch (ex: Exception) {
                    retainedInstance.canClose.value = true
                    return@launch
                }
                retainedInstance.sendedCount.value.inc()
            }

            retainedInstance.canClose.value = true
            return@launch
        }
    }

    private suspend fun sendDocument(document: Document) {

        val products = repository.getProductsByDocument(documentId = document.id)

        val infSysId = repository.sendDocument(
            document = document,
            products = products
        )

        repository.updateDocument(document = document, infSysId = infSysId)
    }

    inner class DialogRetainedInstance : InstanceKeeper.Instance {

        val canClose = MutableStateFlow(false)
        val coroutineScope = componentCoroutineScope()
        val state = MutableStateFlow<ListDialogState>(ListDialogState.Hidden)

        val documentCount = MutableStateFlow(0)
        val sendedCount = MutableStateFlow(0)

        override fun onDestroy() {
            coroutineScope.cancel()
        }

        init {
            state.onEach {
                when (it) {
                    ListDialogState.Hidden -> {
                        canClose.value = false
                        documentCount.value = 0
                        sendedCount.value = 0
                    }

                    ListDialogState.SendDocuments -> startSendDocuments()
                }
            }.launchIn(coroutineScope)
        }
    }
}