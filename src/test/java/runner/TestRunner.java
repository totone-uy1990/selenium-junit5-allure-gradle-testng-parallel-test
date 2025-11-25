package runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource
        ("features")
@ConfigurationParameter
        (key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter
        (key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter
        (key = GLUE_PROPERTY_NAME, value = "steps")

//allure reports
@ConfigurationParameter
        (key = PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")

public class TestRunner {
}
