object Libs {
    object Coroutines {
        private const val version = "1.7.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object Decompose {

        private const val version = "2.0.0"

        const val core = "com.arkivanov.decompose:decompose:$version"
        const val compose = "com.arkivanov.decompose:extensions-compose-jetpack:$version"
        const val android = "com.arkivanov.decompose:extensions-android:$version"

    }

    object Koin {

        private const val version = "3.4.0"

        const val core = "io.insert-koin:koin-android:$version"
        const val android = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"
    }
}