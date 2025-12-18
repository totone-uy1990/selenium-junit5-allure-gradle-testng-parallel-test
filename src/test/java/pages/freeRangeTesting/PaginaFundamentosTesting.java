package pages.freeRangeTesting;

import org.openqa.selenium.WebElement;
import pages.BasePage;

public class PaginaFundamentosTesting extends BasePage {
    private String introduccionAlTestingLink = "";

    public void clickIntroduccionTestingLink() {
        clickElement(introduccionAlTestingLink);
    }


    @Override
    protected WebElement getElement(String locator) {
        return getWebElement(locator);
    }
}
