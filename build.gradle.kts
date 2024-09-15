plugins {
    kotlin("jvm") version "2.0.20"
    
    application
    
    id("com.gradleup.shadow") version "8.3.1"
}

group = "com.swiftsoftwaregroup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("com.swiftsoftwaregroup.clikt.MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.swiftsoftwaregroup.clikt.MainKt"
    }
}

tasks.shadowJar {
    archiveBaseName.set("cli-kt")
    archiveClassifier.set("")
    archiveVersion.set("")
}