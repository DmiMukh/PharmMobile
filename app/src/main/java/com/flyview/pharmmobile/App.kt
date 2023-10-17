package com.flyview.pharmmobile

import android.app.Application
import android.content.Context
import com.flyview.core.ComponentFactory
import com.flyview.core.KoinProvider
import com.flyview.documents_feature.DocumentsComponentFactory
import com.flyview.inventory_feature.InventoryComponentFactory
import org.koin.core.Koin

class App : Application(), KoinProvider {

    override lateinit var koin: Koin
        private set

    override fun onCreate() {
        super.onCreate()
        koin = createKoin()
    }

    private fun createKoin(): Koin {
        return Koin().apply {
            loadModules(allModules)
            declare(this@App as Application)
            declare(this@App as Context)
            declare(ComponentFactory(this))
            declare(InventoryComponentFactory(this))
            declare(DocumentsComponentFactory(this))
            createEagerInstances()
        }
    }
}