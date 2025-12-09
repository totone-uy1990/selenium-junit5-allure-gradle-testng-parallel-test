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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                // ----------------------------------------
                // CONFIGURACIÓN BROWSERSTACK (Nube)
                // ----------------------------------------
                HashMap<String, Object> bstackOptions = new HashMap<>();
                bstackOptions.put("os", "Windows");
                bstackOptions.put("osVersion", "11");
                bstackOptions.put("sessionName", "Parallel Test");
                options.setCapability("bstack:options", bstackOptions);
                // Nota: BrowserStack no suele necesitar headless=new explícito si quieres ver el video después

                driver.set(new RemoteWebDriver(new URL(REMOTE_URL), options));

            } else {
                // ----------------------------------------
                // CONFIGURACIÓN LOCAL vs GITHUB ACTIONS
                // ----------------------------------------
                options.addArguments("--remote-allow-origins=*");

                // Detectamos si estamos en GitHub Actions buscando la variable "CI"
                String isCI = System.getenv("CI");

                if (isCI != null && isCI.equals("true")) {
                    // --- GITHUB ACTIONS (HEADLESS) ---
                    System.out.println("Entorno CI (GitHub) detectado: Ejecutando Headless");
                    options.addArguments("--headless=new"); // Fundamental para servidores sin pantalla
                    options.addArguments("--window-size=1920,1080"); // Simula pantalla Full HD
                    options.addArguments("--no-sandbox"); // Requerido para contenedores Docker/Linux
                    options.addArguments("--disable-dev-shm-usage"); // Evita crash por memoria compartida
                    options.addArguments("--disable-gpu");
                } else {
                    // --- TU PC LOCAL (CON PANTALLA) ---
                    System.out.println("Entorno LOCAL detectado: Ejecutando navegador visual");
                    options.addArguments("--start-maximized");
                }

                driver.set(new ChromeDriver(options));
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Si no estamos en modo headless, aseguramos maximizar (aunque window-size ya ayuda en CI)
        if (driver.get() != null) {
            // driver.get().manage().window().maximize(); // Opcional, ya lo cubrimos con los argumentos arriba
        }
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

    public List<String> getDropDownValues(String locator) {
        Select dropdown = new Select(find(locator));
        List<String> listaOpciones = new ArrayList<>();
        List<WebElement> listaValores = dropdown.getOptions();
        for (WebElement element : listaValores) {
            listaOpciones.add(element.toString());
        }
        return listaOpciones;
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