package pages.freeRangeTesting;

import org.openqa.selenium.WebElement;
import pages.BasePage;

public class PaginaPrincipal extends BasePage {
    //usamos string format (%s)
    //String sectionLink = "nav li a[href*=\"%s\"]"; //css
   private String sectionLink = "//a[normalize-space()='%s' and @href]";

    //Generals
    public void navigateToFreeRange() {
        navigateTo("https://www.freerangetesters.com/");
    }


    public void clicklOnSectionNavigationBar(String section) {
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);
    }

    @Override
    protected WebElement getElement(String locator) {
        return getWebElement(locator);
    }

}
