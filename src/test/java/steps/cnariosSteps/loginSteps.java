package steps.cnariosSteps;

import assertions.CustomAssertions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.cnarios.LoginPageTest;
import io.cucumber.datatable.DataTable;
import steps.pojos.LoginData;

public class loginSteps {
    final private LoginPageTest loginFlow = new LoginPageTest();
    final private CustomAssertions verify = new CustomAssertions();


    @Given("the user is on the Cnarios test_login page {string}")
    public void navigatetoLoginFlow(String url) {
        loginFlow.navigateTo(url);
    }


    @When("the user enters his credentials:")
    public void fillingUsernameField(DataTable table) {
        LoginData data = table.asList(LoginData.class).get(0);
        loginFlow.writeUsernameField(data.getUsername());
        loginFlow.writePaswordField(data.getPassword());

    }

    @And("clicks on the login button")
    public void clickingButtonLogin() {
        loginFlow.clickButton();
    }


    @Then("the user should see an error message saying {string}")
    public void validatingErrorMessageLogin(String message) {
        verify.assertTextEquals(loginFlow.getMessageWelcome(), message);
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


    //metodo adicional para campos vacios


}
