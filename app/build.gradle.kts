plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kumar.crackup"
    compileSdk  = 37

    defaultConfig {
        applicationId = "com.kumar.crackup"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
//
        debug {
            isMinifyEnabled = false
            isDebuggable = true

        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.compose.material3)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.compose.animation)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.core)

    //navigation
    implementation(libs.hilt.navigation.compose) // Check latest version

    //Coil - For loading internet images in jetpack compose similar to glide
    implementation(libs.coil.kt.coil.compose)
    implementation(libs.androidx.core.splashscreen)


    //room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    //hilt
    //added for hilt for di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //RETROFIT
    implementation(libs.retrofit2.retrofit) // Use the latest version

    //GSON converter
    implementation(libs.retrofit2.converter.gson) // Use the latest version

    //HTTP client and logging interceptor
    implementation(libs.okhttp3.okhttp) // Use the latest version compatible with your Retrofit
    implementation(libs.okhttp3.logging.interceptor) // Optional, for logging requests/responses

    //seialization
    implementation(libs.kotlinx.serialization.json)

//    implementation("com.github.kostub:iosmath:0.1.5")
//    implementation("com.github.lingochamp:katexview:1.0.2")
}
