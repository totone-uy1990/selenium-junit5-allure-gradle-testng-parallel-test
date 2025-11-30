package runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")

@ConfigurationParameter(
        key = FEATURES_PROPERTY_NAME,
        value = "src/test/resources/features"
)
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "steps"
)

// Allure + JSON necesario
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, json:build/allure-results/cucumber-report.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class TestRunner {
}
