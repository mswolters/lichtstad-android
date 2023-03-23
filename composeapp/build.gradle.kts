@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}

val composeVersion = "1.4.0"
val accompanistVersion  = "0.30.0"

android {
    namespace = "com.move4mobile.lichtstad"
    compileSdk = 33
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    flavorDimensions += "whitelabel"
    productFlavors {
        create("lichtstad") {
            dimension = "whitelabel"
            applicationId = "nl.gramsbergen.oranjevereniging.lichtstad.compose"
        }
    }
}

dependencies {
    // NOTE: The original intention was to replace this with a maven dependency
    // when that would become available. However, it was moved into the material-components-android library
    // and the methods used to generate a full material color scheme from 1 color are marked internal.
    // So let's just keep this dependency so we don't have to mess around before compiling.
    // Check https://github.com/material-foundation/material-color-utilities
    implementation(project(":composeapp:material-color-utilities"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    //HUGE AND LARGE, make sure minify strips unused icons!
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")

    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.1")

    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")

    implementation("androidx.activity:activity-compose:1.7.0")

    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    implementation("io.coil-kt:coil-compose:2.2.2")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}