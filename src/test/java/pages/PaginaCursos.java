package pages;

public class PaginaCursos extends BasePage {
    String sectionLink = "//h3[normalize-space()='Introducción al Testing de Software']";

    public void clickOnFundamentosDelTestingLink() {
        clickElement(sectionLink);
    }


}
