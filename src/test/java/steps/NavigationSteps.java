package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.BasePage;

public class NavigationSteps {

    BasePage base = new BasePage();

    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        base.navigateTo(url);
    }

    @Then("The page title should contain {string}")
    public void the_page_title_should_contain(String text) {
        String title = BasePage.getDriver().getTitle();
        System.out.println("Thread " + Thread.currentThread().getId() + " → " + title);

    }
}
