package com.flyview.pharmmobile

import android.app.Application
import android.content.Context
import com.flyview.pharmmobile.core.ComponentFactory
import com.flyview.pharmmobile.core.KoinProvider
import org.koin.core.Koin
import org.koin.core.module.Module

private val allModules = listOf<Module>(
)

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
            createEagerInstances()
        }
    }
}