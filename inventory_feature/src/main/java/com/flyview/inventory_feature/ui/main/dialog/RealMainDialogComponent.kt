package com.flyview.inventory_feature.ui.main.dialog

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.flyview.core.utils.componentCoroutineScope
import com.flyview.inventory_feature.domain.InventoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RealMainDialogComponent(
    componentContext: ComponentContext,
    private val onDismiss: () -> Unit,
    private val repository: InventoryRepository
) : ComponentContext by componentContext, MainDialogComponent {

    private val retainedInstance = instanceKeeper.getOrCreate(::DialogRetainedInstance)

    override val canClose = retainedInstance.canClose
    override val state = retainedInstance.state
    override val articulsLoadComplete = retainedInstance.articulsLoadComplete
    override val certificatesLoadComplete = retainedInstance.certificatesLoadComplete
    override val marksLoadComplete = retainedInstance.marksLoadComplete

    override fun onCloseClick() {
        state.value = MainDialogState.Hidden
    }

    override fun onDismissClick() {
        if (retainedInstance.canClose.value) {
            state.value = MainDialogState.Hidden
            return
            this.onDismiss.invoke()
        }
    }

    inner class DialogRetainedInstance : InstanceKeeper.Instance {
        // The scope survives Android configuration changes
        val coroutineScope = componentCoroutineScope()
        private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        val state = MutableStateFlow<MainDialogState>(MainDialogState.Hidden)

        val canClose = MutableStateFlow(false)

        val articulsLoadComplete = MutableStateFlow<LoadState>(LoadState.Loading)
        val certificatesLoadComplete = MutableStateFlow<LoadState>(LoadState.Loading)
        val marksLoadComplete = MutableStateFlow<LoadState>(LoadState.Loading)

        private fun uploadData() {
            scope.launch {
                val articuls = async {
                    val loaded = repository.uploadArticuls()
                    articulsLoadComplete.value = if (loaded) LoadState.OK else LoadState.Cancel
                }

                val certificates = async {
                    val loaded = repository.uploadCertificates()
                    certificatesLoadComplete.value = if (loaded) LoadState.OK else LoadState.Cancel
                }

                val marks = async {
                    val loaded = repository.uploadMarks()
                    marksLoadComplete.value = if (loaded) LoadState.OK else LoadState.Cancel
                }

                articuls.await()
                certificates.await()
                marks.await()

                canClose.value = true
                return@launch
            }
        }

        override fun onDestroy() {
            Log.d("DESTROY", "plak=plak")
            scope.cancel() // Cancel the scope when the instance is destroyed
            coroutineScope.cancel()
        }

        init {
            state.onEach {
                when (it) {
                    MainDialogState.Hidden -> {
                        articulsLoadComplete.value = LoadState.Loading
                        certificatesLoadComplete.value = LoadState.Loading
                        marksLoadComplete.value = LoadState.Loading
                        canClose.value = false
                    }

                    MainDialogState.UploadData -> uploadData()
                }
            }.launchIn(coroutineScope)
        }
    }
}