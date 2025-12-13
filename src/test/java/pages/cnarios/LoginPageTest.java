package pages.cnarios;

import pages.BasePage;

public class LoginPageTest extends BasePage {
    String TestLoginFlow = "https://www.cnarios.com/challenges/login-flow";
    String userNameField = "";
    String paswordField = "";
    String loginButon = "";

    public void navigateToTestLogin() {
        navigateTo(TestLoginFlow);
    }

    public void writeUsernameField(String words) {
        write(userNameField, words);
    }

    public void writePaswordField(String words) {
        write(paswordField, words);
    }

    public void clickButton() {
        clickElement(loginButon);
    }

}
