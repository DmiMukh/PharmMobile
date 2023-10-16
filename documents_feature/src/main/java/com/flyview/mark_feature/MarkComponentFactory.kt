package com.flyview.mark_feature

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

class MarkComponentFactory(private val localKoin: Koin) : KoinComponent {

    override fun getKoin(): Koin = localKoin
}