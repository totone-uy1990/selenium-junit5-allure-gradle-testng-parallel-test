package pages.cnarios;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class LoginPageTest extends BasePage {
    private String TestLoginFlow = "https://www.cnarios.com/challenges/login-flow";
    private String userNameField = "(//input[contains(@class,'MuiOutlinedInput-input')])[1]";
    private String paswordField = "(//input[contains(@class,'MuiOutlinedInput-input')])[2]";
    private String loginButon = "button.MuiButton-containedPrimary.MuiButton-fullWidth";
    private String messageAdmin = "(//div[contains(@class,'MuiAlert-message css-127h8j3')])[2]";
    private String DashBoardLocator = "//p[contains(text(), 'Dashboard')]";
    private String welcomeMessage = "//h5[contains(text(), 'Welcome')]";
    private String alerts = "div[role='alert']";

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

    public WebElement getMessageWelcome() {
        return getElement(welcomeMessage);
    }

    @Override
    protected WebElement getElement(String locator) {
        return getWebElement(locator);
    }


    public List<WebElement> getErrorMessages() {
        return findElements(alerts);
    }


}
