package pages;

import customExceptions.AutomationException;
import customExceptions.FrameworkException;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class BasePage {
    //metodo obligatorio para todos los page object
    protected abstract WebElement getElement(String locator);

    // Driver por hilo (clave del paralelismo)
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static ChromeOptions options;

    // --------------------------
    // DRIVER INIT
    // --------------------------
    public static void initDriver() {

        options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);// omision paginas peligrosas
        options.addArguments("--remote-allow-origins=*");
        // ----------------------------------------
        // CONFIGURACIÓN LOCAL vs GITHUB ACTIONS
        // ----------------------------------------

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
            // ---PC LOCAL (CON PANTALLA) ---
            System.out.println("Entorno LOCAL detectado: Ejecutando navegador visual");
            options.addArguments("--start-maximized");
        }

        driver.set(new ChromeDriver(options));

    }

    // --------------------------
    // GETTERS
    // --------------------------

    //otenemos el driver del threadLocal
    public static WebDriver getDriverFromThread() {
        return driver.get();
    }

    //creamos la espera con el driver
    private WebDriverWait getWait() {
        return new WebDriverWait(getDriverFromThread(), Duration.ofSeconds(10));
    }

    // --------------------------
    // NAVIGATION
    // --------------------------
    public void navigateTo(String url) {
        getDriverFromThread().get(url);
    }

    // --------------------------
    // LOCATORS
    // --------------------------
    //detecta si es css o xpath
    private static By getBy(String locator) {
        locator = locator.trim();

        if (locator.startsWith("/") || locator.startsWith("(")) {
            return By.xpath(locator);
        } else {
            return By.cssSelector(locator);
        }
    }

    private WebElement find(String locator) {
        try {
            // Selenium intenta buscar el elemento
            return getWait()
                    .until(ExpectedConditions
                            .presenceOfElementLocated
                                    (getBy(locator)));
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            // Capturamos el error técnico de Selenium y lo "envolvemos" en tu FrameworkException
            throw new FrameworkException("No se pudo encontrar el elemento con el localizador: "
                    + locator + " tras el tiempo de espera configurado.", e);
        }
    }

    // --------------------------
    // ACTIONS
    // --------------------------
// En BasePage.java
    public void clickElement(String locator) {
        find(locator).click();
    }

    public void write(String locator, String keysToSend) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(normalizeText(keysToSend));
    }

    // DROPDOWN methods

    public Select selectDropdown(String locator) {
        Select dropdwon = new Select(find(locator));
        return dropdwon;
    }


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

    public List<String> getDropdownOptionsText(Select select) {
        return select.getOptions()
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }


    //isDisplayed:
    public boolean elementIsDisplayed(String locator) {
        if (find(locator).isDisplayed()) {
            System.out.println("El elemento es visible");
        } else {
            System.out.println("El elemento no es visible");

        }
        return find(locator).isDisplayed();
    }


    public WebElement getWebElement(String locator) {
        return find(locator);

    }

    public String getTextOfWebElement(String locator) {
        return find(locator).getText();
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


    //normalizador de textos
    private String normalizeText(String value) {
        if (value == null) {
            return null;   // o "" según tu criterio
        }
        String actualText = value.trim().replace("\"", "");
        return switch (actualText) {
            case "EMPTY" -> "";
            case "SPACE" -> " ";
            default -> actualText;
        };
    }

}