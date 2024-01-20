@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

val composeVersion = "1.5.4"
val accompanistVersion  = "0.32.0"

android {
    namespace = "nl.drbreakalot.lichtstad"
    compileSdk = 34
    defaultConfig {
        applicationId = "nl.gramsbergen.oranjevereniging.lichtstad"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "compose-rewrite"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isDebuggable = false
            // todo: signingconfig?
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        // We need java's LocalDate while kotlinx-datetime doesn't have formatters
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }

    flavorDimensions += "whitelabel"
    productFlavors {
        create("lichtstad") {
            dimension = "whitelabel"
            applicationId = "nl.gramsbergen.oranjevereniging.lichtstad"
        }
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // NOTE: The original intention was to replace this with a maven dependency
    // when that would become available. However, it was moved into the material-components-android library
    // and the methods used to generate a full material color scheme from 1 color are marked internal.
    // So let's just keep this dependency so we don't have to mess around before compiling.
    // Check https://github.com/material-foundation/material-color-utilities
    implementation(project(":composeapp:material-color-utilities"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    //HUGE AND LARGE, make sure minify strips unused icons!
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")

    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")

    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")

    implementation("androidx.activity:activity-compose:1.8.2")

    implementation("androidx.navigation:navigation-compose:2.7.6")

    implementation("com.google.maps.android:maps-compose:4.3.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation("io.insert-koin:koin-androidx-compose:3.5.3")
    implementation("io.insert-koin:koin-androidx-compose-navigation:3.5.3")

    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-database")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}