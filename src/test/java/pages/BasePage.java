package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BasePage {

    // BrowserStack variables
    private static final String USERNAME = System.getenv("BS_USER");
    private static final String ACCESS_KEY = System.getenv("BS_KEY");
    private static final String REMOTE_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    // Driver por hilo (clave del paralelismo)
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static ChromeOptions options;

    // --------------------------
    // DRIVER INIT
    // --------------------------
    public static void initDriver() {
        try {
            options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);

            if (USERNAME != null && ACCESS_KEY != null) {
                // BrowserStack capabilities
                HashMap<String, Object> bstackOptions = new HashMap<>();
                bstackOptions.put("os", "Windows");
                bstackOptions.put("osVersion", "11");
                bstackOptions.put("sessionName", "Parallel Test");
                options.addArguments("--headless=new");
                options.setCapability("bstack:options", bstackOptions);

                driver.set(new RemoteWebDriver(new URL(REMOTE_URL), options));

            } else {
                // LOCAL
                System.out.println("Ejecutando en local...");
                options.addArguments("--remote-allow-origins=*");
                driver.set(new ChromeDriver(options));
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.get().manage().window().maximize();
    }

    // --------------------------
    // GETTERS
    // --------------------------
    public static WebDriver getDriver() {
        return driver.get();
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5));
    }

    // --------------------------
    // NAVIGATION
    // --------------------------
    public void navigateTo(String url) {
        getDriver().get(url);
    }

    // --------------------------
    // LOCATORS
    // --------------------------
    private static By getBy(String locator) {
        locator = locator.trim();

        if (locator.startsWith("/") || locator.startsWith("(")) {
            return By.xpath(locator);
        } else {
            return By.cssSelector(locator);
        }
    }

    private WebElement find(String locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(getBy(locator)));
    }

    // --------------------------
    // ACTIONS
    // --------------------------
    public void clickElement(String locator) {
        find(locator).click();
    }

    public void write(String locator, String keysToSend) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(keysToSend);
    }

    // DROPDOWN methods
    public void selectFromDropdownByValue(String locator, String value) {
        new Select(find(locator)).selectByValue(value);
    }

    public void selectFromDropdownByIndex(String locator, int index) {
        new Select(find(locator)).selectByIndex(index);
    }

    public int dropdownSize(String locator) {
        return new Select(find(locator)).getOptions().size();
    }

    public void selectFromDropDown(String locator, String text) {
        new Select(find(locator)).selectByVisibleText(text);
    }

    // --------------------------
    // CLOSE DRIVER
    // --------------------------
    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // ← CLAVE PARA PARALELISMO REAL
        }
    }
}
