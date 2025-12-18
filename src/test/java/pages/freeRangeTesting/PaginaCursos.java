package pages.freeRangeTesting;

import org.openqa.selenium.WebElement;
import pages.BasePage;

public class PaginaCursos extends BasePage {
    String sectionLink = "//h3[normalize-space()='Introducción al Testing de Software']";

    public void clickOnFundamentosDelTestingLink() {
        clickElement(sectionLink);
    }


    @Override
    protected WebElement getElement(String locator) {
        return getWebElement(locator);
    }
}
