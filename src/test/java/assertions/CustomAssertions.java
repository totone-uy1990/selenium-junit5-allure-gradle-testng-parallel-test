package assertions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class CustomAssertions {

    //elemento visible
    public  void AssertElementIsDisplayed(WebElement webElement, String elementName) {

        try {
            Assert.assertTrue(webElement.isDisplayed(),
                    elementName + "deberia estar visible");
            Reporter.log("[INFO] " + elementName + " Esta visible");
        } catch (Exception e) {
            Reporter.log("[ERROR] " + elementName + " NO está visible " + e.getMessage(), true);
            e.getMessage();
        }
    }

    public void assertTextEquals(WebElement element, String expectedText, String fieldName) {
        String actualText = element.getText().trim();
        Assert.assertEquals(actualText, expectedText,
                "Texto en " + fieldName + " no coincide. Esperado: '" +
                        expectedText + "', Actual: '" + actualText + "'");
    }


    public static void assertContainsText(WebElement element, String expectedText, String fieldName) {
        String actualText = element.getText();
        Assert.assertTrue(actualText.contains(expectedText),
                fieldName + " debería contener '" + expectedText +
                        "', pero contiene: '" + actualText + "'");
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
