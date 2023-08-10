package com.flyview.inventory_feature

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

class InventoryComponentFactory(private val localKoin: Koin) : KoinComponent {

    override fun getKoin(): Koin = localKoin
}