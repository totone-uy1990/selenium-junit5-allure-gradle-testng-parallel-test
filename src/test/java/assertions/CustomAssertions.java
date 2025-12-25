package assertions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CustomAssertions {

    // --------------------------
    // VISIBILIDAD
    // --------------------------
    public void assertElementIsDisplayed(WebElement element, String elementName) {
        Assertions.assertTrue(
                element.isDisplayed(),
                "El elemento '" + elementName + "' debería estar visible"
        );
    }

    // --------------------------
    // TEXTO EXACTO
    // --------------------------
    public void assertTextEquals(WebElement element, String expectedText, String fieldName) {
        Assertions.assertEquals(
                expectedText,
                element.getText().trim(),
                "Fallo de negocio en '" + fieldName + "'"
        );
    }

    // --------------------------
    // TEXTO CONTENIDO
    // --------------------------
    public void assertContainsText(WebElement element, String expectedText, String fieldName) {
        Assertions.assertTrue(
                element.getText().contains(expectedText),
                "El campo '" + fieldName + "' no contiene el texto esperado: " + expectedText
        );
    }

    // --------------------------
    // ESTADO
    // --------------------------
    public void assertElementIsEnabled(WebElement element, String elementName) {
        Assertions.assertTrue(
                element.isEnabled(),
                "El elemento '" + elementName + "' debería estar habilitado"
        );
    }

    public void assertElementIsSelected(WebElement element, String elementName) {
        Assertions.assertTrue(
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

        Assertions.assertEquals(
                expectedList,
                actualList,
                "El dropdown '" + dropdownName + "' no coincide con la lista esperada"
        );
    }

    // --------------------------
    // REGLAS DE NEGOCIO
    // --------------------------
    public void failBusinessRule(String message) {
        Assertions.fail(message);
    }
}
