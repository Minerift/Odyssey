plugins {
    `java-library`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly(libs.spigot.api)
}

group = "odyssey"
version = "1.0-SNAPSHOT"
description = "Odyssey"
java.sourceCompatibility = JavaVersion.VERSION_21
