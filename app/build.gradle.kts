plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt)
}

android {
    namespace = "ru.ll.catalogtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.ll.catalogtest"
        minSdk = 24
        targetSdk = 34
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
    //Logging
    implementation(libs.timber)
    //    hilt
    implementation (libs.hilt)
    kapt(libs.dagger)
    //    retrofit
    implementation(libs.retrofit)
    //    retrofit-adapter
    implementation(libs.adapter)
    //okhttp
    implementation(libs.okhttp)
//    moshi
    implementation(libs.moshi)
//logging-interceptor
    implementation(libs.interceptor)
    //    coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    // coroutines
    implementation(libs.coroutines)
    implementation(libs.lifecycle)
}
