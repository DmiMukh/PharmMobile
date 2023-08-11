package com.flyview.inventory_feature

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.inventory_feature.data.RealInventoryRepository
import com.flyview.inventory_feature.domain.Document
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.ui.RealInventoryRootComponent
import com.flyview.inventory_feature.ui.InventoryRootComponent
import com.flyview.inventory_feature.ui.details.DocumentDetailsComponent
import com.flyview.inventory_feature.ui.details.RealDocumentDetailsComponent
import com.flyview.inventory_feature.ui.list.DocumentListComponent
import com.flyview.inventory_feature.ui.list.RealDocumentListComponent
import com.flyview.inventory_feature.ui.main.MainComponent
import com.flyview.inventory_feature.ui.main.RealMainComponent
import org.koin.core.component.get
import org.koin.dsl.module

val inventoryModule = module {
    single<InventoryRepository> {
        RealInventoryRepository(db = get())
    }
}

fun ComponentFactory.createInventoryComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): InventoryRootComponent {
    return RealInventoryRootComponent(
        componentContext = componentContext,
        onBack = onBack,
        componentFactory = get()
    )
}

fun InventoryComponentFactory.createInventoryDetailsComponent(
    componentContext: ComponentContext,
    document: Document,
    onBack: () -> Unit
): DocumentDetailsComponent {
    return RealDocumentDetailsComponent(
        componentContext = componentContext,
        document = document,
        onBack = onBack,
        repository = get(),
        barcodeReader = get()
    )
}

fun InventoryComponentFactory.createInventoryListComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit,
    onDocumentDetails: (Document) -> Unit
): DocumentListComponent {
    return RealDocumentListComponent(
        componentContext = componentContext,
        onBack = onBack,
        onDocumentDetails = onDocumentDetails,
        repository = get()
    )
}

fun InventoryComponentFactory.createInventoryMainComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit,
    onDocumentsClick: () -> Unit
): MainComponent {
    return RealMainComponent(
        componentContext = componentContext,
        onBack = onBack,
        onDocumentsClick = onDocumentsClick
    )
}
