plugins {
    id("java-library")
}

//NOTE, this entire module was yoinked from https://github.com/material-foundation/material-color-utilities
//it has not been published on maven yet. Remove when it is.

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation("com.google.errorprone:error_prone_annotations:2.9.0")
}