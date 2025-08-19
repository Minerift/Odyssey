plugins {
    `java-library`
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    implementation(libs.unimi.fastutil)
    compileOnly(libs.spigot.api)
}

group = "odyssey"
version = "1.0-SNAPSHOT"
description = "Odyssey"
java.sourceCompatibility = JavaVersion.VERSION_21
