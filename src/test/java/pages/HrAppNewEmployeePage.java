package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HrAppNewEmployeePage {
    public HrAppNewEmployeePage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@routerlink='/employee/-1']")
    public WebElement createNewEmployeeButton;

    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement lastName;

    @FindBy(id="department")
    public WebElement department;

    @FindBy(id="job")
    public WebElement jobTitle;

    @FindBy(xpath = "//input[@name='salary']")
    public WebElement salary;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    public WebElement saveButton;

    @FindBy(xpath = "//table[@class='table']//td[2]")
    public List<WebElement> firstNames;

    @FindBy(xpath = "//table[@class='table']//td[3]")
    public List<WebElement> lastNames;



}