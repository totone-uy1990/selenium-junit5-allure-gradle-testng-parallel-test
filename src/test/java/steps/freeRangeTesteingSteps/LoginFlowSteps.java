package steps.freeRangeTesteingSteps;

import io.cucumber.java.en.Given;
import pages.cnarios.LoginPageTest;

public class LoginFlowSteps {
    LoginPageTest loginTest = new LoginPageTest();

    @Given("the user is on the Cnarios login page {string}")
    public void the_User_Is_On_LoginTestFlow(String url) {
        loginTest.navigateTo(url);
    }
}
