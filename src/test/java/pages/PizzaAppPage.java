package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PizzaAppPage {

    public PizzaAppPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "pizza1Pizza")
    public WebElement pizzaChoice;

    @FindBy(xpath = "//select[@class='toppings1']")
    public WebElement topping1;

    @FindBy(xpath = "//select[@class='toppings2']")
    public  WebElement topping2;

    @FindBy(id = "pizza1Qty")
    public WebElement quantity;

    @FindBy(id = "pizza1Cost")
    public WebElement cost;

    @FindBy(id = "name")
    public WebElement name;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "phone")
    public WebElement phoneBox;

    @FindBy(id = "cashpayment")
    public WebElement cashpaymentRadioButton;

    @FindBy(id = "ccpayment")
    public WebElement ccpaymentRadioButton;

    @FindBy(id = "placeOrder")
    public WebElement placeOrderButton;

    @FindBy(id = "dialog")
    public WebElement dialogWindow;




}
