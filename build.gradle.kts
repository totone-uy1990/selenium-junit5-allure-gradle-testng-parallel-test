plugins {
    java
    id("io.qameta.allure") version "2.12.0" // Plugin maneja el agente automáticamente
}

repositories {
    mavenCentral()
}

// Versiones consistentes
val allureVersion = "2.29.0" // Actualizado a versión reciente
val cucumberVersion = "7.20.1"
val junitPlatformVersion = "1.11.3"
val seleniumVersion = "4.27.0" // Versión REAL estable

// Configuración del plugin de Allure
allure {
    version.set(allureVersion)
    adapter {
        frameworks {
            junit5 { // JUnit 5 maneja Cucumber en este setup
                adapterVersion.set(allureVersion)
            }
        }
    }
}

dependencies {
    // Cucumber + JUnit Platform
    testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")
    testImplementation("org.junit.platform:junit-platform-suite:$junitPlatformVersion")

    // Allure
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("io.qameta.allure:allure-junit-platform")

    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
}

tasks.test {
    useJUnitPlatform()

    // Configuración para que Allure sepa dónde dejar los resultados
    systemProperty("allure.results.directory", "build/allure-results")

    // Mejor visualización en consola.
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}