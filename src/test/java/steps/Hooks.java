package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.BasePage;

public class Hooks {

    @Before
    public void setup() {
        BasePage.initDriver();
    }

    @After
    public void teardown() {
        BasePage.closeDriver();
    }
}