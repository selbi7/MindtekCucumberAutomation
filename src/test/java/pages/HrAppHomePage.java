package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HrAppHomePage {

    public HrAppHomePage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "(//button[@class='btn btn-success'])[2]")
    public WebElement editButton;

    @FindBy(name = "firstName")
    public WebElement firstname;

    @FindBy(name = "lastName")
    public WebElement lastname;

    @FindBy(id = "department")
    public WebElement departmentName;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[2]")
    public WebElement actualFirstname;

    @FindBy(xpath = "/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[3]")
    public WebElement actualLastname;

    @FindBy(xpath = "/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[4]")
    public WebElement actualDepartment;

    @FindBy(xpath = "(//td[@_ngcontent-c2])[7]")
    public WebElement idToDelete;

    @FindBy(xpath = "(//button[@class='btn btn-warning'])[2]")
    public WebElement deleteButton;

    @FindBy(xpath = "/html/body/app-root/div[1]/app-employee-details/div[2]/input")
    public WebElement searchBox;

    @FindBy(xpath = "//td[2]")
    public WebElement firstNameEmployee;

    @FindBy(xpath = "//td[3]")
    public WebElement lastNameEmployee;

    @FindBy(xpath = "//td[4]")
    public WebElement departmentId;

    @FindBy(id="searchButton")
    public WebElement searchButton;

    @FindBy(id = "department")
    public WebElement departmentsDropDown;




}
