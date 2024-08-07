import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.kotlin.cocoapods.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.serialization.get().pluginId)
    id(libs.plugins.jetbrains.compose.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        framework {
                baseName = "commonPresentation"
            isStatic = true
        }
    }

    task("testClasses")

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common.domain)

            api(libs.lifecycle.viewmodel)
            api(libs.lifecycle.viewmodel.compose)

            api(libs.koin.compose)
        }
    }
}

android {
    namespace = "com.psc.rickandmorty.core.common.presentation"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}