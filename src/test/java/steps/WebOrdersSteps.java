package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.WebOrdersHomePage;
import pages.WebOrdersOrderspage;
import pages.WebOrdersLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class WebOrdersSteps {
    WebDriver driver= Driver.getDriver();
    WebOrdersLoginPage loginPage= new WebOrdersLoginPage();
    WebOrdersOrderspage orderPage= new WebOrdersOrderspage();
    WebOrdersHomePage homePage=new WebOrdersHomePage();
    List<Map<String,Object>> data;
    int numberOfRows;

    @Given("user navigates to WebOrders application")
    public void user_navigates_to_WebOrders_application() {

        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("user provides username {string} and password {string} and clicks on login button")
    public void user_provides_username_and_password_clicks_on_login_button(String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.logInButton.click();

    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
        String actualTitle=driver.getTitle();
        String expected="Web Orders";
        Assert.assertEquals(expected,actualTitle);
        driver.quit();

    }

    @Then("user validates error message {string}")
    public void user_validates_error_message(String errorMessage) {
        String actualMessage=loginPage.errorMessage.getText();
        Assert.assertEquals(errorMessage,actualMessage);
        driver.quit();

    }

    @And("user clicks on Order module")
    public void userClicksOnOrderModule() {
        orderPage.orderModule.click();
        
    }

    @And("user selects {string} product with Integer {int} quantity")
    public void userSelectsProductWithIntegerQuantityQuantity(String product, int quantity) {
        BrowserUtils.selectDropdownByValue(orderPage.orderProductDropDown,product);
        orderPage.orderQuantity.sendKeys(Keys.BACK_SPACE);
        orderPage.orderQuantity.sendKeys(quantity+""+Keys.ENTER);

    }

    @Then("user validates total is calculated correctly for quantity {int}")
    public void userValidatesTotalIsCalculatedCorrectlyForQuantityQuantity(int quantity) {
        int pricePerUnit=Integer.parseInt(orderPage.pricePerUnit.getAttribute("value"));
        int discount=Integer.parseInt(orderPage.discountBox.getAttribute("value"));
        int actualTotal=Integer.parseInt(orderPage.orderTotal.getAttribute("value"));

        int expectedTotal=0;
        if(expectedTotal==0){
            expectedTotal=quantity*pricePerUnit;
        }else {
            expectedTotal=discount*pricePerUnit/100;
            Assert.assertEquals(expectedTotal,actualTotal);
        }
    }

    @When("user creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
        data= dataTable.asMaps(String.class,Object.class);
        System.out.println(data.get(0).get("order"));
        BrowserUtils.selectDropdownByValue(orderPage.orderProductDropDown,data.get(0).get("order").toString());
        orderPage.orderQuantity.sendKeys(Keys.BACK_SPACE);
        orderPage.orderQuantity.sendKeys(data.get(0).get("quantity").toString());
        orderPage.customerName.sendKeys(data.get(0).get("name").toString());
        orderPage.street.sendKeys(data.get(0).get("street").toString());
        orderPage.city.sendKeys(data.get(0).get("city").toString());
        orderPage.state.sendKeys(data.get(0).get("state").toString());
        orderPage.zipCode.sendKeys(data.get(0).get("zip").toString());
        orderPage.visaCheckBox.click();
        orderPage.cardNumber.sendKeys(data.get(0).get("cc").toString());
        orderPage.expDate.sendKeys(data.get(0).get("expire date").toString());
        orderPage.processButton.click();
    }

    @Then("user validates success message {string}")
    public void user_validates_success_message(String expectedMessage) {
       String actualMessage=orderPage.successMessage.getText();
       Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Then("user validates order added to List of Orders")
    public void user_validates_order_added_to_List_of_Orders() {
       homePage.allOrdersModule.click();
       int numberOfRowsAfterOrder= homePage.numberOfRows.size();
        Assert.assertEquals(numberOfRowsAfterOrder-numberOfRows,1);
        String actualFirstName= homePage.firstNameInTheRow.getText();
        Assert.assertEquals(data.get(0).get("name").toString(),actualFirstName);
        //Homework is to do the rest of the validations for the rest of the Data in the Row.

    }

    @And("user counts number of orders in table")
    public void userCountsNumberOfOrdersInTable() {
        numberOfRows=homePage.numberOfRows.size();

    }
}
