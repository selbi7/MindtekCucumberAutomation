package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import pojos.hrapipojos.Department;
import pojos.hrapipojos.Location;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class HRDepartmentsAPISteps {
    Response response;
    Integer departmentId;


    @Given("user creates departments with departments post call having random data")
    public void user_creates_departments_with_departments_post_call_having_random_data() {
        Department departmentBody= new Department();
        Random random= new Random();
        departmentId= random.nextInt(999);
        departmentBody.setDepartmentId(departmentId);
        departmentBody.setDepartmentName("Selbi"+departmentId);
        Location location= new Location();
        location.setLocationCity("Brooklyn");
        location.setLocationCountry("US");
        location.setLocationId(12345);
        location.setLocationState("NY");
        departmentBody.setLocation(location);
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().body(departmentBody)
                        .when().post("api/departments");

    }

    @Then("user validates statusCode {int}")
    public void user_validates_status_code_is(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);


    }

    @When("user gets created department")
    public void user_gets_created_department() {
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("api/departments/"+departmentId);

    }

    @Then("user validates created department is in departments dropdown")
    public void user_validates_created_department_is_in_departments_dropdown() {
        String uiDepartment= Driver.getDriver().findElement(By.xpath("//option[@value='"+departmentId+"']")).getText();
        Assert.assertEquals("Selbi"+departmentId,uiDepartment);

    }

    @When("user deletes created department")
    public void user_deletes_created_department() {
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .when().delete("api/departments/"+departmentId);

    }







}
