package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HrAppLoginPage;
import pages.HrAppNewEmployeePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HrAppSteps {
    WebDriver driver= Driver.getDriver();
    HrAppLoginPage hrAppLoginPage=new HrAppLoginPage();
    HrAppNewEmployeePage hrAppNewEmployeePage=new HrAppNewEmployeePage();

    @Given("User navigates to HR App {string} Page")
    public void user_navigates_to_HR_App_Page(String hrAppUrl) {
        driver.get(hrAppUrl);

    }

    @When("user enters {string} for username and {string} for password and clicks login button")
    public void user_enters_for_username_and_for_password_and_clicks_login_button(String username, String password) {
        hrAppLoginPage.username.sendKeys(username);
        hrAppLoginPage.password.sendKeys(password);
        hrAppLoginPage.loginButton.click();

    }

    @Then("user lands on the {string} page where he sees the list of the employees")
    public void user_lands_on_the_page_where_he_sees_the_list_of_the_employees(String mainPageURL) {
        String actualURL= driver.getCurrentUrl();
        Assert.assertEquals(actualURL,mainPageURL);
    }


    @Then("{string} message is displayed")
    public void message_is_displayed(String errorMessage) {
        String actualErrorMessage=hrAppLoginPage.errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, errorMessage);
    }

    @Then("user clicks on logout button and verifies {string} message is displayed")
    public void userClicksOnLogoutButtonAndVerifiesMessageIsDisplayed(String expectedMessage) {
        hrAppLoginPage.logoutButton.click();
        String actualMessage=hrAppLoginPage.actualMessage.getText();
        Assert.assertEquals(actualMessage,expectedMessage);
    }


    @And("User clicks on Create new employee tab")
    public void userClicksOnCreateNewEmployeeTab() {
        hrAppNewEmployeePage.createNewEmployeeButton.click();

    }

    String firstName="John";
    String lastName="Doe";

    @And("User provides data for the input fields and clicks on SAVE button")
    public void userProvidesDataForTheInputFieldsAndClicksOnSAVEButton() throws InterruptedException {
        driver.navigate().refresh();

        hrAppNewEmployeePage.firstName.sendKeys(firstName);
        hrAppNewEmployeePage.lastName.sendKeys(lastName);

        BrowserUtils.selectDropdownByValue(hrAppNewEmployeePage.department,"20");
        BrowserUtils.selectDropdownByValue(hrAppNewEmployeePage.jobTitle,"SA_MAN");

        hrAppNewEmployeePage.salary.clear();
        hrAppNewEmployeePage.salary.sendKeys("50000");

        hrAppNewEmployeePage.saveButton.click();
        driver.navigate().refresh();

    }


    @Then("User validates new employee is created")
    public void userValidatesNewEmployeeIsCreated() {
        String firstName1;
        String lastName1;

        for(int i=0; i<hrAppNewEmployeePage.firstNames.size(); i++){
            if(hrAppNewEmployeePage.firstNames.get(i).toString().equals(firstName)){
                firstName1=hrAppNewEmployeePage.firstNames.get(i).toString();
                Assert.assertEquals(firstName1,firstName);
                break;
            }
        }

        for(int i=0; i<hrAppNewEmployeePage.lastNames.size(); i++){
            if(hrAppNewEmployeePage.lastNames.get(i).toString().equals(lastName)){
                lastName1=hrAppNewEmployeePage.lastNames.get(i).toString();
                Assert.assertEquals(lastName1,lastName);
                break;

            }
        }
    }

}