package com.flyview.documents_feature

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.documents_feature.data.api.FakeDocumentsApi
import com.flyview.documents_feature.data.repository.DocumentsRepositoryImpl
import com.flyview.documents_feature.data.repository.MarkRepositoryImpl
import com.flyview.documents_feature.data.repository.PlacementRepositoryImpl
import com.flyview.documents_feature.domain.api.DocumentsApi
import com.flyview.documents_feature.domain.model.Document
import com.flyview.documents_feature.domain.model.Product
import com.flyview.documents_feature.domain.repository.DocumentsRepository
import com.flyview.documents_feature.domain.repository.MarkRepository
import com.flyview.documents_feature.domain.repository.PlacementRepository
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
import com.flyview.pharmmobile.documents_feature.DocDatabase
import org.koin.core.component.get
import org.koin.dsl.module

val documentsModule = module {
    single<DocumentsApi> { FakeDocumentsApi() /*RealDocumentsApi()*/ }
    single<DocDatabase> { provideDocDatabase(provideDocSqlDriver(get())) }
    single<DocumentsRepository> {
        DocumentsRepositoryImpl(
            api = get(),
            db = get()
        )
    }

    single<MarkRepository> {
        MarkRepositoryImpl()
    }

    single<PlacementRepository> {
        PlacementRepositoryImpl(
            db = get()
        )
    }
}

fun provideDocSqlDriver(app: Application): SqlDriver {
    return AndroidSqliteDriver(
        schema = DocDatabase.Schema,
        context = app,
        name = "doc.db"
    )
}

fun provideDocDatabase(driver: SqlDriver): DocDatabase {
    return DocDatabase(driver)
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
    onBack: () -> Unit,
    onScanClick: () -> Unit,
    onPlacementClick: () -> Unit
): DocumentComponent {
    return RealDocumentComponent(
        componentContext = componentContext,
        currentDocument = document,
        onBack = onBack,
        onScanClick = onScanClick,
        onPlacementClick = onPlacementClick
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
    product: Product,
    onBack: () -> Unit
): MarkListComponent {
    return RealMarkListComponent(
        componentContext = componentContext,
        currentProduct = product,
        onBack = onBack,
        audioPlayer = get(),
        messageService = get(),
        repository = get()
    )
}

fun DocumentsComponentFactory.cratePlacementComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit
): PlacementComponent {
    return RealPlacementComponent(
        componentContext = componentContext,
        onBack = onBack,
        audioPlayer = get(),
        messageService = get(),
        repository = get<PlacementRepository>()
    )
}

fun DocumentsComponentFactory.createProductListComponent(
    componentContext: ComponentContext,
    document: Document,
    onBack: () -> Unit
): ProductListComponent {
    return RealProductListComponent(
        componentContext = componentContext,
        currentDocument = document,
        onBack = onBack,
        audioPlayer = get(),
        messageService = get(),
        repository = get()
    )
}