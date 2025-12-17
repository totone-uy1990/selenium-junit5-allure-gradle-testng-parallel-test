package pages.cnarios;

import org.openqa.selenium.NoSuchElementException;
import pages.BasePage;

public class LoginPageTest extends BasePage {
    String TestLoginFlow = "https://www.cnarios.com/challenges/login-flow";
    String userNameField = "(//input[contains(@class,'MuiOutlinedInput-input')])[1]";

    String paswordField = "(//input[contains(@class,'MuiOutlinedInput-input')])[2]";
    String loginButon = "button.MuiButton-containedPrimary.MuiButton-fullWidth";

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

}
