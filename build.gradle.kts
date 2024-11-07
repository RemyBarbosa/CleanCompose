// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.android.library") version "8.7.2" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
}

extra["compileSdkVersion"] = 35
extra["minSdkVersion"] = 24
extra["jvmTarget"] = "17"
