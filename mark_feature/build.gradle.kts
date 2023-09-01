plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.flyview.mark_feature"
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
}

dependencies {

    // Modules
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

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