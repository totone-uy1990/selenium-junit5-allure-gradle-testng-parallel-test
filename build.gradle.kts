plugins {
    id 'java'
    id 'io.qameta.allure' version '2.12.0'
}

group = 'com.example'
version = '1.0'

repositories {
    mavenCentral()
}

dependencies {
    // JUnit + Cucumber + Platform
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.2'
    testImplementation 'io.cucumber:cucumber-java:7.14.0'
    testImplementation 'io.cucumber:cucumber-junit-platform-engine:7.14.0'

    // Allure
    testImplementation 'io.qameta.allure:allure-java-commons:2.24.0'
    testImplementation 'io.qameta.allure:allure-cucumber7-jvm:2.24.0'

    // Opcional para WebDriver
    testImplementation 'org.seleniumhq.selenium:selenium-java:4.22.0'
}

test {
    useJUnitPlatform()

    systemProperty "allure.results.directory", "target/allure-results"

    testLogging {
        events "passed", "failed", "skipped"
    }
}

allure {
    autoconfigure = true
    aspectjweaver = true
    version = "2.24.0"
}

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
}
