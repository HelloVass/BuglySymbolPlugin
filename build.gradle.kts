buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.github.HelloVass:BuglySymbolPlugin:1.0.5")
    }
}

group = "info.hellovass.bugly"
version = "1.0.0-SNAPSHOT"

subprojects {

    group = rootProject.group
    version = rootProject.version

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

val clean by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}