package assertions;

import customExceptions.VerificationException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class CustomAssertions {

    // --------------------------
    // VISIBILIDAD
    // --------------------------
    public void assertElementIsDisplayed(WebElement element, String elementName) {
        Assert.assertTrue(
                element.isDisplayed(),
                "El elemento '" + elementName + "' debería estar visible"
        );
    }

    // --------------------------
    // TEXTO EXACTO
    // --------------------------
    public void assertTextEquals(WebElement element, String expectedText, String fieldName) {
        Assert.assertEquals(
                element.getText().trim(),
                expectedText,
                "Fallo de negocio en '" + fieldName + "'"
        );
    }

    // --------------------------
    // TEXTO CONTENIDO
    // --------------------------
    public void assertContainsText(WebElement element, String expectedText, String fieldName) {
        Assert.assertTrue(
                element.getText().contains(expectedText),
                "El campo '" + fieldName + "' no contiene el texto esperado: " + expectedText
        );
    }

    // --------------------------
    // ESTADO
    // --------------------------
    public void assertElementIsEnabled(WebElement element, String elementName) {
        Assert.assertTrue(
                element.isEnabled(),
                "El elemento '" + elementName + "' debería estar habilitado"
        );
    }

    public void assertElementIsSelected(WebElement element, String elementName) {
        Assert.assertTrue(
                element.isSelected(),
                "El elemento '" + elementName + "' debería estar seleccionado"
        );
    }

    // --------------------------
    // DROPDOWN
    // --------------------------
    public void assertEqualsDropDownList(
            String dropdownName,
            Select dropdownSelect,
            List<String> expectedList) {

        List<String> actualList = dropdownSelect.getOptions()
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        Assert.assertEquals(
                actualList,
                expectedList,
                "El dropdown '" + dropdownName + "' no coincide con la lista esperada"
        );
    }

    // --------------------------
    // REGLAS DE NEGOCIO (EJEMPLO)
    // --------------------------
    public void failBusinessRule(String message) {
        throw new VerificationException(message);
    }
}
