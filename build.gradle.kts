plugins {
    id("java")
    kotlin("jvm") version "2.0.21"
    id("com.typewritermc.module-plugin") version "1.1.2"
}

group = "net.momirealms"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.momirealms.net/releases/")
    maven("https://maven.typewritermc.com/beta/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("net.momirealms:custom-crops:3.6.37")
}

typewriter {
    namespace = "customcrops"
    extension {
        name = "CustomCrops"
        shortDescription = "Integrate CustomCrops with Typewriter."
        description = "The CustomCrops extension provides event triggers for most CustomCrops API events, allowing you to further customize the server with cool features."
        engineVersion = "0.8.0"
        paper {
            dependency("CustomCrops")
        }
    }
}

kotlin {
    jvmToolchain(21)
}