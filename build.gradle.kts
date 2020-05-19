import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.72"
    id( "com.github.johnrengelman.shadow") version "5.2.0"
}

group = "de.hasan"
version = "0.0.1"

repositories {
    mavenCentral()
    jcenter()
}

application {
    mainClassName = "ApplicationKt"
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.github.scribejava:scribejava-core:6.9.0")

    implementation("io.ktor:ktor-server-netty:1.3.1")
    implementation("io.ktor:ktor-jackson:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-assembly:0.7.1")

    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.+")
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

sourceSets {
    main {
        resources {
            srcDir("${project(":frontend").rootDir}/frontend/dist/")
        }
    }
}
