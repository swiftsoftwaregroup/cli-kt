plugins {
    kotlin("jvm") version "2.0.20"
    application
    id("com.gradleup.shadow") version "8.3.1"
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "com.swiftsoftwaregroup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")

	testImplementation(platform("org.junit:junit-bom:5.11.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
    
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")    
}


testlogger {
    theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
    showExceptions = true
    showStackTraces = true
    showFullStackTraces = false
    showCauses = true
    slowThreshold = 2000
    showSummary = true
    showSimpleNames = false
    showPassed = true
    showSkipped = true
    showFailed = true
    showStandardStreams = false
    showPassedStandardStreams = true
    showSkippedStandardStreams = true
    showFailedStandardStreams = true
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

tasks.test {
    useJUnitPlatform()
}
