package pages.cnarios;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class LoginPageTest extends BasePage {
    public String TestLoginFlow = "https://www.cnarios.com/challenges/login-flow";
    public String userNameField = "(//input[contains(@class,'MuiOutlinedInput-input')])[1]";

    String paswordField = "(//input[contains(@class,'MuiOutlinedInput-input')])[2]";
    String loginButon = "button.MuiButton-containedPrimary.MuiButton-fullWidth";
    String messageAdmin = "(//div[contains(@class,'MuiAlert-message css-127h8j3')])[2]";
    String DashBoardLocator = "//p[contains(text(), 'Dashboard')]";

    public void navigateToTestLogin() {
        navigateTo(TestLoginFlow);
    }

    public void writeUsernameField(String words) throws NoSuchElementException {
        write(userNameField, words);
    }

    public void writePaswordField(String words) {
        write(paswordField, words);

    }

    public void clickButton() {
        clickElement(loginButon);

    }

    //validadores
    public String getAdminLoggedMessage(String locator) {
        return getTextOfWebElement(locator);
    }

    public WebElement adminMessageElement() {
        return getElement(messageAdmin);

    }

    public WebElement dashBoardElement() {
        return getElement(DashBoardLocator);
    }

    @Override
    protected WebElement getElement(String locator) {
        return getWebElement(locator);
    }


}
