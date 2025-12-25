plugins {
    id("java")
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
val testNG="7.11.0"
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
    // JUnit 5 Assertions
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.1")

    // Allure
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("io.qameta.allure:allure-junit-platform")

    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")

//TestNG para Assertions
    //testImplementation("org.testng:testng:$testNG")
}



// Configuración para forzar la codificación UTF-8 en la compilación
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// Configuración recomendada para asegurar que los tests también usen UTF-8
tasks.withType<Test> {
    systemProperty("file.encoding", "UTF-8")
}


java {
    toolchain {
        // Asegúrate de tener la versión correcta de Java aquí (e.g., 21, 17)
        // Y que esté alineada con tu AspectJ si tienes ese problema pendiente.
         languageVersion.set(JavaLanguageVersion.of(21))
    }
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