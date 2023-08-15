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

    object Moko {

    }

    object Paging {
        private const val version = "3.1.1"

        const val core = "androidx.paging:paging-runtime:$version"

        // optional - Jetpack Compose integration
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha18"
    }

    object SQLDelight {

        const val version = "2.0.0"

        const val android = "app.cash.sqldelight:android-driver:$version"

        const val plugin = "app.cash.sqldelight"

        const val coroutines = "app.cash.sqldelight:coroutines-extensions:$version"

        const val paging = "app.cash.sqldelight:androidx-paging3-extensions:$version"
    }

    object UsbSerial {
        private const val version = "3.5.1"
        const val android = "com.github.mik3y:usb-serial-for-android:$version"
    }
}