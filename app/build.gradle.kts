plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.ll.catalogtest"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.ll.catalogtest"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    debugImplementation(libs.androidx.ui.tooling)
    //Logging
    implementation(libs.timber)
    //    hilt
    implementation(libs.hilt)
    kapt(libs.dagger)
    implementation("com.squareup:javapoet:1.13.0")
    //    retrofit
    implementation(libs.retrofit)
    //    retrofit-adapter
    implementation(libs.adapter)
    //okhttp
    implementation(libs.okhttp)
//    moshi
    implementation(libs.moshi)
    implementation(libs.squareup.moshi)
//logging-interceptor
    implementation(libs.interceptor)
    //    coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    // coroutines
    implementation(libs.coroutines)
    implementation(libs.lifecycle)
    //navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.android.hilt.compose)
}
