package com.flyview.inventory_feature

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.arkivanov.decompose.ComponentContext
import com.flyview.core.ComponentFactory
import com.flyview.inventory_feature.data.InventoryApi
import com.flyview.inventory_feature.data.InventoryApiImpl
import com.flyview.inventory_feature.data.InventoryRepositoryImpl
import com.flyview.inventory_feature.domain.InventoryRepository
import com.flyview.inventory_feature.domain.model.Document
import com.flyview.inventory_feature.domain.model.Product
import com.flyview.inventory_feature.ui.InventoryRootComponent
import com.flyview.inventory_feature.ui.RealInventoryRootComponent
import com.flyview.inventory_feature.ui.details.DocumentDetailsComponent
import com.flyview.inventory_feature.ui.details.RealDocumentDetailsComponent
import com.flyview.inventory_feature.ui.list.DocumentListComponent
import com.flyview.inventory_feature.ui.list.RealDocumentListComponent
import com.flyview.inventory_feature.ui.main.MainComponent
import com.flyview.inventory_feature.ui.main.RealMainComponent
import com.flyview.inventory_feature.ui.product_edit.ProductEditComponent
import com.flyview.inventory_feature.ui.product_edit.RealProductEditComponent
import com.flyview.pharmmobile.inventory_feature.InventoryDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.component.get
import org.koin.dsl.module

val inventoryModule = module {
    single<InventoryApi> { InventoryApiImpl(client = provideKtorHttpClient(), storage = get()) }
    single<InventoryDatabase> { provideInventoryDatabase(provideInventorySqlDriver(get())) }
    single<InventoryRepository> {
        InventoryRepositoryImpl(
            db = get(),
            api = get(),
            storage = get(),
            messageService = get()
        )
    }
}

fun provideKtorHttpClient(): HttpClient {
    return HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    encodeDefaults = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                }
            )
        }

        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value

                when (statusCode) {
                    in 300..399 -> throw RedirectResponseException(response, response.toString())
                    in 400..499 -> throw ClientRequestException(response, response.toString())
                    in 500..599 -> throw ServerResponseException(response, response.toString())
                }

                if (statusCode >= 600) {
                    throw ResponseException(response, response.toString())
                }
            }

            handleResponseExceptionWithRequest { cause, _ -> throw cause }
        }
    }
}

fun provideInventorySqlDriver(app: Application): SqlDriver {
    return AndroidSqliteDriver(
        schema = InventoryDatabase.Schema,
        context = app,
        name = "inventory.db"
    )
}

fun provideInventoryDatabase(driver: SqlDriver): InventoryDatabase {
    return InventoryDatabase(driver)
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
    onBack: () -> Unit,
    onEditProduct: (Product) -> Unit
): DocumentDetailsComponent {
    return RealDocumentDetailsComponent(
        componentContext = componentContext,
        document = document,
        onBack = onBack,
        onEditProduct = onEditProduct,
        repository = get(),
        barcodeReader = get(),
        messageService = get(),
        audioPlayer = get()
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
        repository = get(),
        messageService = get()
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
        onDocumentsClick = onDocumentsClick,
        repository = get(),
        messageService = get(),
        storage = get()
    )
}

fun InventoryComponentFactory.createOnventoryEditComponent(
    componentContext: ComponentContext,
    onBack: () -> Unit,
    product: Product,
    documentId: Long
): ProductEditComponent {
    return RealProductEditComponent(
        componentContext = componentContext,
        onBack = onBack,
        product = product,
        documentId = documentId,
        repository = get()
    )
}