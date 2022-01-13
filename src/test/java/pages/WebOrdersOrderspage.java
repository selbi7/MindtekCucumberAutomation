package pages;

import io.cucumber.java.bs.I;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WebOrdersOrderspage {

    public WebOrdersOrderspage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='Process.aspx']")
    public WebElement orderModule;

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement orderProductDropDown;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement orderQuantity;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
    public WebElement pricePerUnit;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtDiscount")
    public WebElement discountBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtTotal")
    public WebElement orderTotal;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerName;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement street;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement city;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement state;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipCode;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumber;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expDate;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement visaCheckBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(tagName = "strong")
    public WebElement successMessage;

}