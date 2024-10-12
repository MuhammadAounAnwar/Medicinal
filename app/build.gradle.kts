plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("androidx.room")

    /*alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp.devtools)
    alias(libs.plugins.androidx.room)*/
}

android {
    namespace = "com.ono.medicinal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ono.medicinal"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "option_name" to "option_value",
                    // other options...
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.androidx.core)
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation(libs.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)

    // Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation(libs.room.ktx)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Paging (optional)
    implementation(libs.paging.runtime)

    // Kotlin Coroutines Core
    implementation(libs.coroutines.core)

    // For UI work with coroutines (e.g., with Jetpack Compose)
    implementation(libs.coroutines.android)

    // JUnit (core unit testing framework)
    testImplementation(libs.junit)

    // Mocking libraries for unit tests
    testImplementation(libs.mockk)                // MockK for mocking
    testImplementation(libs.mockito.core)         // Mockito for unit tests
    testImplementation(libs.mockito.kotlin)       // Mockito Kotlin extension
    androidTestImplementation(libs.mockk.mockk.android)

    // Coroutines testing support
    testImplementation(libs.kotlinx.coroutines.test)

    // Jetpack Compose UI testing
    androidTestImplementation(libs.compose.ui.test.junit4)  // Main Compose testing library
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM for Compose dependencies

    // Navigation testing for NavController
    androidTestImplementation(libs.androidx.navigation.testing)

    // AndroidX Core for instrumentation tests
    androidTestImplementation(libs.androidx.core)

    // Espresso (for Android UI tests)
    androidTestImplementation(libs.espresso.core)

    // AndroidX JUnit (extensions for JUnit on Android)
    androidTestImplementation(libs.androidx.junit)

    // Debugging tools for Jetpack Compose
    debugImplementation(libs.compose.ui.tooling)         // UI tooling for Compose
    debugImplementation(libs.compose.ui.test.manifest)   // Test manifest for Compose
    androidTestImplementation(libs.compose.ui.test.manifest) // For manifest configuration


}

kapt {
    correctErrorTypes = true
}

