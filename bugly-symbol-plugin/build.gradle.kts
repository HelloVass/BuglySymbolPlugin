plugins {
    `kotlin-dsl`
    `maven-publish`
    id("org.jetbrains.dokka") version "1.5.31"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(gradleApi())
    compileOnly("com.android.tools.build:gradle:4.1.3")
    implementation("com.jaredrummler:ktsh:1.0.0")
}

gradlePlugin {
    plugins {
        create("info.hellovass.bugly") {
            id = name
            implementationClass = "info.hellovass.bugly.BuglySymbolPlugin"
        }
    }
    isAutomatedPublishing = false
}

val sourcesJar by tasks.registering(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    dependsOn("dokkaHtml")
    archiveClassifier.set("javadoc")
    from(tasks["dokkaHtml"])
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            this.groupId = "${project.group}"
            this.artifactId = project.name
            this.version = "${project.version}"

            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
        }
    }
}