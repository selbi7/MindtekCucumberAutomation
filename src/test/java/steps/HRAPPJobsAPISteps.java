package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HrAppHomePage;
import pages.HrAppNewEmployeePage;
import pojos.hrapipojos.Job;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class HRAPPJobsAPISteps {
    Response response;
    Job job;
    HrAppNewEmployeePage hrAppNewEmployeePage= new HrAppNewEmployeePage();
    WebDriver driver= Driver.getDriver();


    @Given("user creates job with POST api call")
    public void user_creates_job_with_POST_api_call() {
        job = new Job();
        job.setJobId("IT_IT");
        job.setTitle("SDET");
        job.setSalary(150000.0);
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Accept","application/json")
                        .and().body(job)
                        .when().post("/api/jobs");

        Assert.assertEquals(201, response.statusCode());

    }

    @Then("user validates that job was created in UI in job title dropdown in Create new employee page")
    public void user_validates_that_job_was_created_in_UI_in_job_title_dropdown_in_Create_new_employee_page() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(hrAppNewEmployeePage.createNewEmployeeButton));
        Thread.sleep(2000);
        hrAppNewEmployeePage.createNewEmployeeButton.click();
        String uiJob= Driver.getDriver().findElement(By.xpath("//option[@value='"+job.getJobId()+"']")).getText();
        Assert.assertEquals(job.getTitle(),uiJob);

    }

    @Then("user validate get jobs api call response includes created job")
    public void user_validate_get_jobs_api_call_response_includes_created_job_GET_Endpoint_api_jobs() {
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/api/jobs");

        String createdJobId= response.body().jsonPath().getString("jobId");
        Assert.assertTrue(createdJobId.contains(job.getJobId()));

    }

    @Then("user validate get job api call responding created job")
    public void user_validate_get_job_api_call_responding_created_job_GET_Endpoint_api_jobs() {
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/api/jobs/"+job.getJobId());
        String actualJobId = response.body().jsonPath().getString("jobId");
        String actualJobTitle= response.body().jsonPath().getString("title");
        Double actualSalary = response.body().jsonPath().getDouble("salary");

        Assert.assertEquals(job.getJobId(),actualJobId);
        Assert.assertEquals(job.getTitle(),actualJobTitle);
        Assert.assertEquals(job.getSalary(),actualSalary);
    }

    @When("user sends Delete api call to the new created job")
    public void user_sends_Delete_api_call_to_the_new_created_job() {
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().delete("/api/jobs/"+job.getJobId());

    }

    @Then("user validates job is not shown in UI in Job Title dropdown")
    public void user_validates_job_is_not_shown_in_UI_in_Job_Title_dropdown() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(hrAppNewEmployeePage.createNewEmployeeButton));
        Thread.sleep(2000);
        hrAppNewEmployeePage.createNewEmployeeButton.click();
        Select select = new Select(hrAppNewEmployeePage.jobTitle);
        List<WebElement> data = select.getOptions();
        for(WebElement e : data){
            Assert.assertFalse(job.getTitle().contains(e.getText()));
        }

    }

    @When("user gets job api call responding created job.")
    public void user_gets_job_api_call_responding_created_job() {
        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/api/job/"+job.getJobId());

    }

    @Then("user validates that status code is {int}")
    public void user_validates_that_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode , response.statusCode());

    }









}
