import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {

    // -------------------- Kotlin --------------------
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrains.kotlin.serialization)

    // -------------------- Android --------------------
    alias(libs.plugins.androidApplication)

    // -------------------- Compose --------------------
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    // -------------------- Tools --------------------
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {

    // -------------------- Android --------------------
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    // -------------------- iOS --------------------
    val iosTargets = listOf(
        iosArm64(),
        iosSimulatorArm64()
    )

    iosTargets.forEach { target ->
        target.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    // -------------------- Desktop --------------------
    jvm()

    // -------------------- SourceSets --------------------
    sourceSets {

        // ---------- Common ----------
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.jetbrains.compose.navigation)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            implementation(libs.bundles.ktor)
            implementation(libs.bundles.coil)

            // ⚠️ Room technically не мультиплатформенный
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }

        // ---------- Android ----------
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            implementation(libs.ktor.client.okhttp)
        }

        // ---------- iOS ----------
        val iosMain by creating {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        // ---------- Desktop ----------
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)

            implementation(libs.ktor.client.okhttp)
        }

        // ---------- Tests ----------
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

// -------------------- KSP --------------------
dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
}

// -------------------- Room config --------------------
room {
    schemaDirectory("$projectDir/schemas")
}

// -------------------- Android config --------------------
android {
    namespace = "com.pivnoydevelopment.bookpedia"

    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.pivnoydevelopment.bookpedia"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// -------------------- Debug tools --------------------
dependencies {
    debugImplementation(libs.compose.uiTooling)
}

// -------------------- Desktop --------------------
compose.desktop {
    application {
        mainClass = "com.pivnoydevelopment.bookpedia.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.pivnoydevelopment.bookpedia"
            packageVersion = "1.0.0"
        }
    }
}
