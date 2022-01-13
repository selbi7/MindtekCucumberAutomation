package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.TestSheepHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

public class TestSheepAppSteps {
    WebDriver driver= Driver.getDriver();
    TestSheepHomePage homePage= new TestSheepHomePage();

    @Given("user navigates to TestSheep application")
    public void user_navigates_to_TestSheep_application() {
        driver.get(ConfigReader.getProperty("TestSheepURL"));
    }

    @When("user enters {string} as first number")
    public void user_enters_as_first_number(String int1) {
        homePage.number1.sendKeys(int1.toString());
    }

    @When("user user enters {string} as second number")
    public void user_user_enters_as_second_number(String int1) {
        homePage.number2.sendKeys(int1.toString());
    }
    @When("user selects add {string} operator")
    public void user_selects_operator(String operation) {
    BrowserUtils.selectByText(homePage.operatorDropDown, operation);
    }

    @When("user clicks on calculate button")
    public void user_clicks_on_calculate_button() {
        homePage.calculateButton.click();
    }

    @Then("user validates output is {int}")
    public void user_validates_output_is(Integer expectedOutput) {
        String actualOutput=homePage.answer.getAttribute("value");
        Assert.assertEquals(expectedOutput.toString(),actualOutput);
    }


    @Then("user validates {string} error message")
    public void userValidatesErrorMessage(String errorMessage) {
        String actualErrorMessage=homePage.errorMessage.getText();
        Assert.assertEquals(errorMessage,actualErrorMessage);
    }
}
