package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverSetup;
import utilities.Util;

public class Hooks {

    @Before()
    public void launchBrowser() {
        DriverSetup.launchBrowser(Util.getBrowserName());
    }

    @After()
    public void quiteBrowser(Scenario scenario) {
        DriverSetup.closeBrowser(scenario);
    }

}
