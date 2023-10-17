package com.flyview.documents_feature

import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.documents_feature.data.DocumentsApi
import com.flyview.documents_feature.data.DocumentsApiImpl
import com.flyview.documents_feature.data.DocumentsRepositoryImpl
import com.flyview.documents_feature.domain.DocumentsRepository
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.ui.DocumentsRootComponent
import com.flyview.documents_feature.ui.RealDocumentsRootComponent
import com.flyview.documents_feature.ui.document.DocumentComponent
import com.flyview.documents_feature.ui.document.RealDocumentComponent
import com.flyview.documents_feature.ui.main.MainComponent
import com.flyview.documents_feature.ui.main.RealMainComponent
import com.flyview.documents_feature.ui.placement.PlacementComponent
import com.flyview.documents_feature.ui.placement.RealPlacementComponent
import com.flyview.documents_feature.ui.scan.mark_list.MarkListComponent
import com.flyview.documents_feature.ui.scan.mark_list.RealMarkListComponent
import com.flyview.documents_feature.ui.scan.product_list.ProductListComponent
import com.flyview.documents_feature.ui.scan.product_list.RealProductListComponent
import org.koin.core.component.get
import org.koin.dsl.module

val documentsModule = module {
    single<DocumentsApi> { DocumentsApiImpl() }
    single<DocumentsRepository> {
        DocumentsRepositoryImpl(
            api = get(),
            messageService = get()
        )
    }
}

fun ComponentFactory.createDocumentsComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): DocumentsRootComponent {
    return RealDocumentsRootComponent(
        componentContext = componentContext,
        onBack = onBack,
        componentFactory = get()
    )
}

fun DocumentsComponentFactory.createDocumentComponent(
    componentContext: ComponentContext,
    document: Document,
    onBack: () -> Unit
): DocumentComponent {
    return RealDocumentComponent(
        componentContext = componentContext,
        currentDocument = document,
        onBack = onBack
    )
}

fun DocumentsComponentFactory.createMainComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit,
    onItemClick: (Document) -> Unit
): MainComponent {
    return RealMainComponent(
        componentContext = componentContext,
        onBack = onBack,
        onItemClick = onItemClick,
        repository = get(),
        messageService = get()
    )
}

fun DocumentsComponentFactory.createMarkListComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): MarkListComponent {
    return RealMarkListComponent(
        componentContext = componentContext,
        messageService = get()
    )
}

fun DocumentsComponentFactory.cratePlacementComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): PlacementComponent {
    return RealPlacementComponent(
        componentContext = componentContext,
        onBack = onBack
    )
}

fun DocumentsComponentFactory.createProductListComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): ProductListComponent {
    return RealProductListComponent(
        componentContext = componentContext
    )
}