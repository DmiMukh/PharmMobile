plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id(Libs.SQLDelight.plugin) version Libs.SQLDelight.version
    //id("kotlinx-serialization")
    kotlin(Libs.KotlinX.Serialization.plugin) version Libs.KotlinX.Serialization.pluginVersion
}

android {
    namespace = "com.flyview.inventory_feature"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Modules
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Coroutines
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)

    // Decompose
    implementation(Libs.Decompose.core)
    implementation(Libs.Decompose.compose)
    implementation(Libs.Decompose.android)

    // Koin
    implementation(Libs.Koin.core)
    implementation(Libs.Koin.android)
    implementation(Libs.Koin.compose)

    // KotlinX
    implementation(Libs.KotlinX.Datetime.core)
    implementation(Libs.KotlinX.Serialization.contentNegotation)
    implementation(Libs.KotlinX.Serialization.json)

    // Ktor
    implementation(Libs.Ktor.core)
    implementation(Libs.Ktor.android)
    implementation(Libs.Ktor.serialization)
    implementation(Libs.Ktor.negotiation)
    implementation(Libs.Ktor.logging)

    // Paging
    implementation(Libs.Paging.core)
    implementation(Libs.Paging.compose)

    // SQLDelight
    implementation(Libs.SQLDelight.android)
    implementation(Libs.SQLDelight.coroutines)
    implementation(Libs.SQLDelight.paging)

}

sqldelight {
    databases {
        create("InventoryDatabase") {
            packageName.set("com.flyview.pharmmobile.inventory_feature")
        }
    }
}