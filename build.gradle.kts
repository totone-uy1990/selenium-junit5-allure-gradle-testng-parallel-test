plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

repositories {
    mavenCentral()
}

// Versión única de Allure (no repitas variables).
val allureVersion = "2.25.0"

// Versión de AspectJ
val aspectJVersion = "1.9.20.1"

// Configuración del agente aspectj
val agent: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {

    // ===== ALLURE =====
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))

    // Para Cucumber + TestNG
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")

    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.38.0")

    // AspectJ weaver
    agent("org.aspectj:aspectjweaver:$aspectJVersion")

    testImplementation("io.cucumber:cucumber-java:7.15.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.15.0")

    testImplementation(platform("io.qameta.allure:allure-bom:2.25.0"))
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.2")

    implementation("org.seleniumhq.selenium:selenium-java:4.38.0")
}


// Configure javaagent for test execution.
tasks.test {

}


tasks.test {

    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
    useJUnitPlatform()

    systemProperty("allure.results.directory", "build/allure-results")

    testLogging {
        events("passed", "skipped", "failed")
    }
}