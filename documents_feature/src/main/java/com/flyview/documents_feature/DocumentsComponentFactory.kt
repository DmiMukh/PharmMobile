package com.flyview.documents_feature

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

class DocumentsComponentFactory(private val localKoin: Koin) : KoinComponent {

    override fun getKoin(): Koin = localKoin
}