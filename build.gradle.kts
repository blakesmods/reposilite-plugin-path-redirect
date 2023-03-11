import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("com.github.johnrengelman.shadow") version "7.1.1"
    id("maven-publish")
}

group = "com.blakesmods"
version = "1.0.1"

repositories {
    mavenCentral()
    maven("https://maven.reposilite.com/releases")
}

dependencies {
    compileOnly("com.reposilite:reposilite:3.3.1")
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("com.blakesmods.plugin.pathredirect.PathRedirectPlugin")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("reposilite-plugin-path-redirect-$version.jar")
    destinationDirectory.set(file("$rootDir/output"))
    mergeServiceFiles()
}