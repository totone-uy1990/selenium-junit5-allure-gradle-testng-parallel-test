package assertions;

import com.google.common.base.VerifyException;
import customExceptions.VerificationException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

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

    //ME QUEDE AQUI 21/12/25
    public void assertElementIsEnabled(WebElement element, String elementName) {
        try {
            Assert.assertTrue(element.isEnabled(),
                    elementName
                            + " debería estar habilitado");
        } catch (AssertionError e) {
            throw new VerificationException("EL ELEMENTO "
                    + elementName
                    + " NO SE ENCUENTRA HABILITADO");
        }
    }


    public void assertElementIsSelected(WebElement element, String elementName) {
        try {
            Assert.assertTrue(element.isSelected(),
                    elementName
                            + " debería estar seleccionado");
        } catch (AssertionError e) {
            throw new VerificationException("EL ELEMENTO "
                    + elementName
                    + " DEBERIA ESTAR SELECCIONADO");
        }
    }


    public void asserEqualsDropDownList(String dropdownName, Select dropdownSelect, List<String> expectedList) {

        try {
            List<String> actualList = dropdownSelect.getOptions()
                    .stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .toList();
            Assert.assertEquals(actualList, expectedList, "LA LISTA DEL DROPDOWN DEBERIA SER IGUAL");
        } catch (AssertionError e) {
            throw new VerificationException("LA LISTA Seleccionada: "
                    + dropdownName
                    + " ES IGUAL A LA ESPERADA");
        }
    }


    // Soft Assertions (acumula errores)
    public class SoftAssert {
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
