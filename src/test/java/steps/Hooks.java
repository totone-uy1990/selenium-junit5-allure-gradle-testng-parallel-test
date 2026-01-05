package steps;

import allureUtils.AllureUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class Hooks {

    @Before
    public void setup() {
        BasePage.initDriver();
    }


    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = BasePage.getDriverFromThread();
            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                AllureUtils.attachScreenshot(screenshot);
                Allure.addAttachment(
                        "URL",
                        "text/plain",
                        driver.getCurrentUrl()
                );
                Allure.addAttachment(
                        "Page source",
                        "text/html",
                        driver.getPageSource(),
                        ".html"
                );
            }
        }

        BasePage.closeDriver();
    }


}
