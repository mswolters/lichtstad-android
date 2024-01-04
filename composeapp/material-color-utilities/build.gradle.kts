plugins {
    id("java-library")
}

// NOTE: The original intention was to replace this with a maven dependency
// when that would become available. However, it was moved into the material-components-android library
// and the methods used to generate a full material color scheme from 1 color are marked internal.
// So let's just keep this dependency so we don't have to mess around before compiling.
// Check https://github.com/material-foundation/material-color-utilities

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("com.google.errorprone:error_prone_annotations:2.9.0")
}