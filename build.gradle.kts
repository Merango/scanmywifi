plugins {
    kotlin("jvm") version "1.8.0"
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    
    // Kotlin standard library
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}