package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.HrAppHomePage;
import pojos.hrapipojos.Department;
import pojos.hrapipojos.Employee;
import pojos.hrapipojos.Job;
import pojos.hrapipojos.Location;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class HREmployeeAPISteps {

    HrAppHomePage homePage=new HrAppHomePage();
    List<Map<String, Object>> dbData;
    Response response;
    String employeeId;
    Integer numberOfEmployeesDB;

    @Given("user gets employee from DB with employeeId {int}")
    public void user_gets_employee_from_DB_with_employeeId(Integer employeeId) throws SQLException {
        JDBCUtils.establishConnection();
        dbData=JDBCUtils.runQuery("Select e.first_name, e.last_name, d.department_name  \n" +
                "From employees e Join departments d On e.department_id=d.department_id\n" +
                "Where employee_id="+employeeId);


    }

    @When("user searches for employee with {int} employeeId")
    public void user_searches_for_employee_with_employeeId(Integer employeeId) {

        homePage.searchBox.sendKeys(employeeId.toString()+ Keys.ENTER);
        homePage.searchButton.click();
    }

    @Then("user validates UI data matches with DB")
    public void user_validates_UI_data_matches_with_DB() {
        String uiFirstName=homePage.firstNameEmployee.getText();
        String uiLastName= homePage.lastNameEmployee.getText();
        String uiDepartmentName=homePage.departmentId.getText();

        String dbFirstName=dbData.get(0).get("first_name").toString();
        String dbLastName=dbData.get(0).get("last_name").toString();
        String dbDepartmentName=dbData.get(0).get("department_name").toString();

        Assert.assertEquals(dbFirstName,uiFirstName);
        Assert.assertEquals(dbLastName,uiLastName);
        Assert.assertEquals(dbDepartmentName,uiDepartmentName);

    }

    @When("user gets employee with get employee api call with {int} employeeId")
    public void user_gets_employee_with_get_employee_api_call_with_employeeId(Integer employeeId) {
    response=
            given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                    .and().header("Accept","application/json")
                    .when().get("/api/employees/"+employeeId);
    }

    @Then("user validates {int} statusCode")
    public void user_validates_statusCode(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("user validates API data matches with DB")
    public void user_validates_API_data_matches_with_DB() {
        String dbFirstName = dbData.get(0).get("first_name").toString();
        String dbLastName = dbData.get(0).get("last_name").toString();
        String dbDepartmentName = dbData.get(0).get("department_name").toString();

        String apiFirstName = response.body().jsonPath().getString("firstname");
        String apiLastName = response.body().jsonPath().getString("lastname");
        String apiDepartmentName = response.body().jsonPath().getString("department.departmentName");

        Assert.assertEquals(dbFirstName, apiFirstName);
        Assert.assertEquals(dbLastName, apiLastName);
        Assert.assertEquals(dbDepartmentName, apiDepartmentName);

    }

        @Given("user creates employee with post api call and with data")
        public void user_creates_employee_with_post_api_call_and_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> data= dataTable.asMap(String.class,Object.class);
        /*
        Request POST employee
        1.URL
        2.Headers
        3.Body for request
         */
            Employee employee= new Employee();
            Department department=new Department();
            department.setDepartmentId(10);
            department.setDepartmentName(data.get("departmentName").toString());
            Location location=new Location();
            location.setLocationCity("Los Angeles");
            location.setLocationCountry("US");
            location.setLocationId(222);
            location.setLocationState("LA");
            department.setLocation(location);
            employee.setDepartment(department);
            employee.setFirstName(data.get("firstName").toString());
            employee.setLastName(data.get("lastName").toString());
            employee.setEmployeeId(0);
            Job job=new Job();
            job.setJobId("IT Prog");
            job.setSalary(150000.0);
            job.setTitle("SDET");
            employee.setJob(job);

            response=
                    given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                            .and().header("Content-Type","application/json")
                            .and().header("Accept","application/json")
                            .and().body(employee)
                            .when().post("/api/employees");

            employeeId = response.getBody().jsonPath().getString("employeeId");


        }

        @When("user searches for created employee")
        public void user_searches_for_created_employee() throws InterruptedException {
        Thread.sleep(5000);
           homePage.searchBox.sendKeys(employeeId);
           homePage.searchButton.click();

        }

        @Then("user validates employee is created in UI with data")
        public void user_validates_employee_is_created_in_UI_with_data(io.cucumber.datatable.DataTable dataTable) {
         String uiFirstName=homePage.firstNameEmployee.getText();
         String uiLastName= homePage.lastNameEmployee.getText();
         String uiDepartmentName= homePage.departmentName.getText();
         Map<String, Object> data= dataTable.asMap(String.class,Object.class);
          String expFirstName= data.get("firstName").toString();
          String  expLastName= data.get("lastName").toString();
          String expDepartmentName= data. get("departmentName").toString();

          Assert.assertEquals(expFirstName,uiFirstName);
          Assert.assertEquals(expLastName,uiLastName);
          Assert.assertEquals(expDepartmentName,uiDepartmentName);
    }



    @Given("user gets number of employees in {string} department from DB")
    public void user_gets_number_of_employees_in_department_from_DB(String departmentName) throws SQLException {
        JDBCUtils.establishConnection();
        dbData=JDBCUtils.runQuery("Select Count(e.employee_id) From employees e \n" +
                "JOIN departments d\n" +
                "ON e.department_id=d.department_id\n" +
                "Where d.department_name='"+departmentName+"'");
        numberOfEmployeesDB=Integer.parseInt(dbData.get(0).get("count").toString());

    }

    @When("user selects {string} department from the dropdown")
    public void user_selects_department_from_the_dropdown(String departmentName) throws InterruptedException {

        BrowserUtils.selectByText(homePage.departmentsDropDown,departmentName);
        Thread.sleep(5000);

    }

    @Then("user validates UI number of employees batches with DB number")
    public void user_validates_UI_number_of_employees_batches_with_DB_number() {
        Integer numberOfEmployeesUI=Driver.getDriver().findElements(By.xpath("//tr")).size()-1;
        Assert.assertEquals(numberOfEmployeesDB,numberOfEmployeesUI);
    }

    @When("user gets employees from {string} department with  api call")
    public void user_gets_employees_from_department_with_api_call(String departmentName) throws SQLException {

        JDBCUtils.establishConnection();
        String departmentId =JDBCUtils.runQuery("SELECT department_id From departments \n" +
                "Where department_name='"+departmentName+"'").get(0).get("department_id").toString();


        response=
                given().baseUri(ConfigReader.getProperty("HRAppAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .and().get("/api/departments/"+departmentId+"0/employees");

    }

    @Then("user validates number of employees in API matches with DB number")
    public void user_validates_number_of_employees_in_API_matches_with_DB_number() {
        Employee[] employees =response.body().as(Employee[].class);
        Integer numberOfEmployeesAPI= employees.length;

        Assert.assertEquals(numberOfEmployeesDB, numberOfEmployeesAPI);

    }
}



