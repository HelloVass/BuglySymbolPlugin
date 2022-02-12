import com.android.build.gradle.api.BaseVariant
import info.hellovass.bugly.AppConfig

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("info.hellovass.bugly")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "info.hellovass.bugly.playground"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
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
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

BuglySymbol {

    /**
     *
     */
    enableByVariant { variant: BaseVariant ->
        variant.name.contains("release", true)
    }

    /**
     *
     */
    appConfig = AppConfig(
        /**
         *
         */
        appId = "3a011f29ba",
        /**
         *
         */
        appKey = "8150fc5d-0bb6-4eb6-85a0-8f5733630cf0",
        /**
         *
         */
        bundleId = "info.hellovass.bugly.playground"
    )

    /**
     *
     */
    jdk8Path = "/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/bin/java"
    /**
     *
     */
    buglyToolsPath = "./bugly/buglyqq-upload-symbol.jar"
}