buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
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
    }
}

val clean by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}