package steps.freeRangeTesteingSteps;

import io.cucumber.java.en.*;
import pages.freeRangeTesting.PaginaCursos;
import pages.freeRangeTesting.PaginaPrincipal;

public class FreeRangeSteps {

    PaginaPrincipal landingPage = new PaginaPrincipal();
    PaginaCursos cursosPage = new PaginaCursos();

    @Given("I navigate to www.freerangetesters.com")
    public void InavifateToFRT() {
        landingPage.navigateToFreeRange();
    }

    @When("I go to {word} using the navigation bar")
    public void navigationBarUser(String section) {
        landingPage.clicklOnSectionNavigationBar(section);
    }

    @And("select Introducción al Testing")
    public void navigateToIntro() {
        cursosPage.clickOnFundamentosDelTestingLink();
       }
}
