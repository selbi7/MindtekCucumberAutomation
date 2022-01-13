package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PizzaAppPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Map;

public class PizzaAppSteps {
    WebDriver driver= Driver.getDriver();
    Map<String,Object> data;
    PizzaAppPage pizzaAppPage= new PizzaAppPage();
    String cost;

    @Given("user navigates to pizza application")
    public void user_navigates_to_pizza_application() {
        driver.get(ConfigReader.getProperty("PizzaAppURL"));
    }

    @When("user creates pizza order with data")
    public void user_creates_pizza_order_with_data(DataTable dataTable) throws InterruptedException {

        data=dataTable.asMap(String.class,Object.class);
        BrowserUtils.selectDropdownByValue(pizzaAppPage.pizzaChoice, data.get("Pizza").toString());
        BrowserUtils.selectDropdownByValue(pizzaAppPage.topping1, data.get("Toppings 1").toString());
        BrowserUtils.selectDropdownByValue(pizzaAppPage.topping2, data.get("Toppings 2").toString());
        pizzaAppPage.quantity.sendKeys(data.get("Quantity").toString());
        pizzaAppPage.name.sendKeys(data.get("Name").toString());
        pizzaAppPage.email.sendKeys(data.get("Email").toString());
        pizzaAppPage.phoneBox.sendKeys(data.get("Phone").toString());
        if(data.get("Payment Type").toString().equalsIgnoreCase("Cash on Pickup")) {
            pizzaAppPage.cashpaymentRadioButton.click();
        }else if(data.get("Payment Type").toString().equalsIgnoreCase("Credit Card")){
            pizzaAppPage.ccpaymentRadioButton.click();
        }

        cost=pizzaAppPage.cost.getAttribute("value").toString();
        pizzaAppPage.placeOrderButton.click();
    }

    @Then("user validates that order is created with success message {string} {string}")
    public void userValidatesThatOrderIsCreatedWithSuccessMessage(String success, String pizza) {
        String expectedMessage=success + cost +" "+pizza;
        String actualMessage=pizzaAppPage.dialogWindow.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }
}

