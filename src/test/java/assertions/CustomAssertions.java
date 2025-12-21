package assertions;

import com.google.common.base.VerifyException;
import customExceptions.VerificationException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class CustomAssertions {

    //elemento visible
    public void AssertElementIsDisplayed(WebElement webElement, String elementName) {

        try {
            // Usamos el Assert de TestNG internamente
            Assert.assertTrue(webElement.isDisplayed(),
                    elementName + "deberia estar visible");
            // Reporter.log("[INFO] " + elementName + " Esta visible");
        } catch (AssertionError e) {
            // Si falla, lanzamos nuestra excepción de NEGOCIO con un mensaje claro

            throw new VerificationException("ERROR DE NEGOCIO: El elemento '"
                    + elementName + "' no se mostró en pantalla.");
        }
    }

    public void assertTextEquals(WebElement element, String expectedText, String fieldName) {
        String actualText = element.getText().trim();
        try {
            Assert.assertEquals(actualText, expectedText);
        } catch (AssertionError e) {
            throw new VerificationException("FALLO DE VALIDACIÓN en " + fieldName +
                    ": Se esperaba '"
                    + expectedText
                    + "' pero se encontró '"
                    + actualText + "'");
        }
    }

    public void assertContainsText(WebElement element, String expectedText, String fieldName) {
        String actualText = element.getText();
        try {
            Assert.assertTrue(actualText.contains(expectedText));
        } catch (AssertionError e) {
            throw new VerificationException("EL CONTENIDO NO COINCIDE en "
                    + fieldName +
                    ": Se buscaba '"
                    + expectedText
                    + "' dentro de '"
                    + actualText + "'");
        }
    }

    public static void assertElementIsEnabled(WebElement element, String elementName) {
        Assert.assertTrue(element.isEnabled(),
                elementName + " debería estar habilitado");
    }

    public static void assertElementIsSelected(WebElement element, String elementName) {
        Assert.assertTrue(element.isSelected(),
                elementName + " debería estar seleccionado");
    }

    // Soft Assertions (acumula errores)
    public static class SoftAssert {
        private org.testng.asserts.SoftAssert softAssert = new org.testng.asserts.SoftAssert();

        public void assertElementDisplayed(WebElement element, String message) {
            try {
                softAssert.assertTrue(element.isDisplayed(), message);
            } catch (Exception e) {
                softAssert.fail(message + ": " + e.getMessage());
            }
        }

        public void assertAll() {
            softAssert.assertAll();
        }
    }


}
