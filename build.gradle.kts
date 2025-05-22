plugins {
    kotlin("jvm") version "2.0.10"
    id("org.jetbrains.dokka") version "2.0.0"
}

group = "prog2425.dam1.prueba-calc-propio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.dokkaHtml {
    moduleName.set("TaskManager")

    dokkaSourceSets {
        named("main") {
            moduleName.set("Mi Proyecto Kotlin")
            displayName.set("Main")
            reportUndocumented.set(true)
            skipEmptyPackages.set(true)
        }
    }
}