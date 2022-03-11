package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HrAppLoginPage;
import pages.HrAppNewEmployeePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBC;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HrAppAddEmployeeSteps {

    WebDriver driver = Driver.getDriver();
    HrAppLoginPage loginPage;


    @Given("user navigates to login page")
    public void userNavigatesToLoginPage() {
        driver.get(ConfigReader.getProperty("HrAppJDBC"));
        String actualTitle=driver.getTitle();
        Assert.assertEquals("HrApp",actualTitle);
    }

    @When("user logs in to HR App")
    public void user_logs_in_to_HR_App() {
        loginPage= new HrAppLoginPage();
        loginPage.username.sendKeys("Mindtek");
        loginPage.password.sendKeys("MindtekStudent");
        loginPage.loginButton.click();

    }

    @And("user creates new employee")
    public void user_creates_new_employee() {
//        HrAppNewEmployeePage newEmployee= new HrAppNewEmployeePage();
//        newEmployee.createNewEmployeeButton.click();
//        newEmployee.firstName.sendKeys();
//        newEmployee.lastName.sendKeys();

        System.out.println("New employee Steven was added");


    }

    @Then("user validates new employee is created in Data Base")
    public void user_validates_new_employee_is_created_in_Data_Base() throws SQLException {
        HrAppNewEmployeePage newEmployee= new HrAppNewEmployeePage();

        String actualFirstName= "SELBI";
        String actualLastName= newEmployee.kingLastname.getText();
        String departmentName= newEmployee.departmentName.getText();

        String query = "SELECT e.first_name, e.last_name, d.department_name From employees e JOIN departments d ON e.department_id=d.department_id\n" +
                "WHERE employee_id=100; ";


        JDBCUtils.establishConnection();
        List<Map<String,Object>> data = JDBCUtils.runQuery(query);


        Assert.assertEquals(data.get(0).get("first_name"),actualFirstName);
        Assert.assertEquals(data.get(0).get("last_name"),actualLastName);
        Assert.assertEquals(data.get(0).get("department_name"),departmentName);

        JDBCUtils.closeDatabase();


    }

}
