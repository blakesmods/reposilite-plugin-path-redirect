import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = "com.blakesmods"
version = "1.0.2"

repositories {
    mavenCentral()
    maven("https://maven.reposilite.com/releases")
}

dependencies {
    compileOnly("com.reposilite:reposilite:3.5.6")
}

application {
    mainClass.set("com.blakesmods.plugin.pathredirect.PathRedirectPlugin")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("reposilite-plugin-path-redirect-$version.jar")
    destinationDirectory.set(file("$rootDir/output"))
    mergeServiceFiles()
}