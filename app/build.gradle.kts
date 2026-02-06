plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Use the serialization plugin from the version catalog.
    // If your catalog doesn't have this alias, replace with:
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
}

android {
    namespace = "com.example.portfolio"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.portfolio"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    // Make sure Compose compiler extension matches your Compose library versions.
    // If you use version catalog, you can replace the literal with: libs.versions.compose.compiler.get()
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    // Optional: keep packaging options or other android config here
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Use Compose BOM to align Compose libraries
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.compose.foundation:foundation")

    // ConstraintLayout for Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Icons (optional)
    implementation("androidx.compose.material:material-icons-extended")

    // Navigation
//    implementation("androidx.navigation:navigation-compose:2.8.0-beta06")
//    implementation("androidx.navigation:navigation-animation:2.8.0-beta06")
    implementation("androidx.navigation:navigation-compose:2.7.0")

    // Kotlinx serialization (runtime)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // Custom bottom nav
    implementation("com.canopas.compose-animated-navigationbar:bottombar:1.0.1")

    // Animation
    implementation("androidx.compose.animation:animation")
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.ui)

    // Test & debug implementations
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}