import org.gradle.internal.classpath.Instrumented.systemProperty

plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {

    // -------------------------------
    // CUCUMBER + JUNIT PLATFORM ENGINE
    // -------------------------------
    testImplementation("io.cucumber:cucumber-core:7.18.1")

    testImplementation("io.cucumber:cucumber-java:7.18.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.18.1")

    // -------------------------------
    // SELENIUM
    // -------------------------------
    testImplementation("org.seleniumhq.selenium:selenium-java:4.26.0")

    // -------------------------------
    // ALLURE + CUCUMBER (JUnit Platform compatible)
    // -------------------------------
    testImplementation("io.qameta.allure:allure-cucumber7-jvm:2.29.0")

    // -------------------------------
    // ASSERTIONS (opcional)
    // -------------------------------
    testImplementation("org.assertj:assertj-core:3.26.0")

    // JUnit 5 (para plataforma general)
    testImplementation("org.junit.platform:junit-platform-suite:1.10.2")
}


tasks.test {
    useJUnitPlatform()

    // Paralelo para Cucumber (NO JUnit)
    systemProperty("cucumber.execution.parallel.enabled", "true")
    systemProperty("cucumber.execution.parallel.config.strategy", "fixed")
    systemProperty("cucumber.execution.parallel.config.fixed.parallelism", "4")

    // Carpeta donde Cucumber/Allure escribirá los JSON
    systemProperty("allure.results.directory", "build/allure-results")

    // Logging básico
    testLogging {
        events("passed", "failed", "skipped")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }}