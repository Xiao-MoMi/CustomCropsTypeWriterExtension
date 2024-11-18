plugins {
    id("java")
    kotlin("jvm") version "2.0.21"
    id("com.typewritermc.module-plugin") version "1.0.0"
}

group = "net.momirealms"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.3-R0.1-SNAPSHOT")
    compileOnly("com.github.Xiao-MoMi:Custom-Crops:3.6.18")
}

typewriter {
    engine {
        version = "0.6.1"
    }
    namespace = "customcrops"
    extension {
        name = "CustomCrops"
        shortDescription = "Integrate CustomCrops with Typewriter."
        description = "The CustomCrops extension provides event triggers for most CustomCrops API events, allowing you to further customize the server with cool features."
        paper {
            dependency("CustomCrops")
        }
    }
}

kotlin {
    jvmToolchain(21)
}