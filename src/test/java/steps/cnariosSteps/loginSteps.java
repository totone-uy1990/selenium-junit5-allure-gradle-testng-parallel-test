package steps.cnariosSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.cnarios.LoginPageTest;

public class loginSteps {
    LoginPageTest loginFlow = new LoginPageTest();

    @Given("the user is on the Cnarios test_login page {string}")
    public void navigatetoLoginFlow(String url) {
        loginFlow.navigateTo(url);
    }

    @When("the user enters the username {string}")
    public void fillingUsernameField(String word) {
        loginFlow.writeUsernameField(word);
    }

    @And("the user enters the password {string}")
    public void fillingPaswordField(String word) {
        loginFlow.writePaswordField(word);
    }

    @And("clicks on the login button")
    public void clickingButtonLogin() {
        loginFlow.clickButton();
    }

    @Then("the user should see an error message saying {string}")
    public void validatingErrorMessageLogin() {

    }

    @Then("the user should be redirected to the {string} page")
    public void validatingWeAreDashBoardPanel(String word) {

    }

    @And("the user should see a welcome message containing {string}")
    public void verifyWelcomeMessage(String word) {

    }


    @When("the user leaves the username field empty")
    public void theUserLeavesTheUsernameFieldEmpty() {

    }



    @When("the user leaves the password field empty")
    public void theUserLeavesThePasswordFieldEmpty() {
    }



    @When("clicks on the login button")
    public void clicksOnTheLoginButton() {

    }



    @Then("the user should see a validation message {string} under the username field")
    public void theUserShouldSeeAValidationMessageUnderTheUsernameField(String message) {

    }



    @Then("the user should see a validation message {string} under the password field")
    public void theUserShouldSeeAValidationMessageUnderThePasswordField(String message) {

    }




}
