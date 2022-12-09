package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverSetup;
import utilities.Util;
import utilities.Locators;
import java.time.Duration;

public class test_steps {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public test_steps(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Given("User navigates to Securian Financial page")
    public void userNavigatesToSecurianFinancialPage() {
        driver.navigate().to(Util.getConfigURL());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.preRetCalcText)));
    }

    @When("User enters {string} in {string} text field")
    public void userEntersInTextField(String value, String field) {
        try{
            String locator =Locators.inputText.replace("<param1>", field);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            driver.findElement(By.xpath(locator)).click();
            driver.findElement(By.xpath(locator)).sendKeys(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @And("User clicks on {string} button")
    public void userClicksOnButton(String buttonText) {
        try{
            driver.findElement(By.xpath(Locators.buttonClick.replace("<param1>", buttonText))).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @And("User selects {string} as {string}")
    public void userSelectsAs(String field, String value) {
        try{
            String locator = Locators.radioSelection.replace("<param1>", field).replace("<param2>", value);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            driver.findElement(By.xpath(locator)).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("User should see results")
    public void userShouldSeeResults() {
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.result)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
