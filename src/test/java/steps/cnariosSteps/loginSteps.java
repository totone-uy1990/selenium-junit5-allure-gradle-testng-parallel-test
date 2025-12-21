package steps.cnariosSteps;

import assertions.CustomAssertions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import pages.cnarios.LoginPageTest;

public class loginSteps {
    final private LoginPageTest loginFlow = new LoginPageTest();
    final private CustomAssertions verify = new CustomAssertions();


    @Given("the user is on the Cnarios test_login page {string}")
    public void navigatetoLoginFlow(String url) {
        loginFlow.navigateTo(url);
    }

    @When("the user enters the username {string}")
    public void fillingUsernameField(String word) {
        try {
            loginFlow.writeUsernameField(word);
        } catch (NoSuchElementException e) {
            String errorMessage = "ERROR: No se pudo encontrar el WebElement";
            throw new RuntimeException(errorMessage, e);
        }

    }

    @And("the user enters the password {string}")
    public void fillingPaswordField(String word) {

        loginFlow.writePaswordField(word);
    }

    @And("clicks on the login button")
    public void clickingButtonLogin() {loginFlow.clickButton();
    }


    @Then("the user should see an error message saying {string}")
    public void validatingErrorMessageLogin(String message) {

    }

    @Then("the user should be redirected to the {string} page")
    public void validatingWeAreDashBoardPanel(String expectedMessage) {
        verify.assertContainsText
                (loginFlow.dashBoardElement(),
                        expectedMessage,
                        "Mensaje de Admin");
    }

    @And("the user should see a welcome message containing {string}")
    public void verifyWelcomeMessage(String expectedMessage) {
        verify.assertContainsText
                (loginFlow.getMessageWelcome(),
                        expectedMessage,
                        "Mensaje de bienvenida");

    }


    @When("the user leaves the username field empty")
    public void theUserLeavesTheUsernameFieldEmpty() {

    }


    @When("the user leaves the password field empty")
    public void theUserLeavesThePasswordFieldEmpty() {
    }


    @Then("the user should see a validation message {string} under the username field")
    public void theUserShouldSeeAValidationMessageUnderTheUsernameField(String message) {

    }


    @Then("the user should see a validation message {string} under the password field")
    public void theUserShouldSeeAValidationMessageUnderThePasswordField(String message) {

    }


}
