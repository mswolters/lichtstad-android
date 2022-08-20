plugins {
    id("com.android.application")
    kotlin("android")
}

val composeVersion = "1.2.1"

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
        //kotlinCompilerExtensionVersion = composeVersion
        kotlinCompilerExtensionVersion = "1.3.0"
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
    // TODO: replace with actual maven dependency when that gets released
    // Check https://github.com/material-foundation/material-color-utilities
    implementation(project(":composeapp:material-color-utilities"))

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")

    implementation("androidx.compose.material3:material3:1.0.0-alpha16")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha16")

    implementation("androidx.activity:activity-compose:1.5.1")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}