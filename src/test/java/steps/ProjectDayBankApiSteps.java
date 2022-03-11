package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.APIUtils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ProjectDayBankApiSteps {

    Response response;

    String customerId;

    String updatedBalance;

    String accountId;

    String updatedAccountType;

    String accountType;

    String transactionId;

    Random random= new Random(Integer.MAX_VALUE);



    Integer randomNum=random.nextInt();

    String expectedTransactionId1=randomNum.toString().substring(0,2);



    @Given("user creates a customer with POST call")
    public void user_creates_a_customer_with_POST_call() {

        Map<String, Object> headers= new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");


        response=APIUtils.apiPost("/api/customer",headers,"{\n" +
                "    \"accountOpenDate\": \"2022-02-27\",\n" +
                "    \"active\": true,\n" +
                "    \"address\": \"123 Ave P\",\n" +
                "    \"fullName\": \"Patel Harsh jr.\",\n" +
                "    \"isActive\": true\n" +
                "}");

        customerId=response.body().jsonPath().getString("id");



    }

    @Then("user validates status code will be {int}")
    public void user_validates_status_code_will_be(Integer statusCode) {
        response.then().statusCode(statusCode);

    }

    @When("user creates an account for created customer with POST call")
    public void user_creates_an_account_for_created_customer_with_POST_call() {

        Map<String, Object> headers= new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");



        response=APIUtils.apiPost("/api/account",headers,"{\n" +
                "          \"accountType\": \"Checking\",\n" +
                "          \"balance\": 50000,\n" +
                "          \"customer\": {\"id\":"+customerId+"}}");

        accountId=response.body().jsonPath().getString("id");
        accountType=response.body().jsonPath().getString("accountType");

        System.out.println(customerId);
        System.out.println(accountId);

    }



    @When("user GETS account created for the customer")
    public void user_GETS_account_created_for_the_customer() {

        Map<String, Object> headers= new HashMap<>();
        headers.put("Accept","application/json");

        response=APIUtils.apiGet("/api/customers/"+customerId,headers);

        String customerFullName="Patel Harsh jr.";
        String actualCustomerName=response.body().jsonPath().getString("fullName");

        Assert.assertEquals(customerFullName,actualCustomerName);

    }



    @When("user updates account balance with PUT call")
    public void user_updates_account_balance_with_PUT_call() {

        Map<String, Object> headers= new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");



        response=APIUtils.apiPut("/api/accounts/"+accountId,headers,"{\n" +
                "    \"accountType\": \"Checking\",\n" +
                "    \"balance\": 6546,\n" +
                "    \"customer\": {\n" +
                "        \"id\": "+customerId+"\n" +
                "    },\n" +
                "    \"id\" : "+accountId+"\n" +
                "}");

        updatedBalance=response.body().jsonPath().getString("balance");
        updatedAccountType=response.body().jsonPath().getString("accountType");

        System.out.println(updatedBalance);
        System.out.println(updatedAccountType);



    }


    @When("user validates that account balance is updated with GET customer call")
    public void user_validates_that_account_balance_is_updated_with_GET_customer_call() {

        Map<String, Object> headers= new HashMap<>();
        headers.put("Accept","application/json");

        response=APIUtils.apiGet("/api/customers/"+customerId,headers);

        String actualUpdatedBalance=response.body().jsonPath().getString("accounts[0].balance");
        String actualAccountType=response.body().jsonPath().getString("accountType");

        Assert.assertEquals(updatedBalance,actualUpdatedBalance);

    }



    @When("user deletes account with DELETE call")
    public void user_deletes_account_with_DELETE_call() {
        Map<String, Object> headers= new HashMap<>();
        headers.put("Accept","application/json");
        response=APIUtils.apiDelete("/api/accounts/"+accountId,headers);
    }



    @When("user deletes customer with DELETE call")
    public void user_deletes_customer_with_DELETE_call() {
        Map<String, Object> headers= new HashMap<>();
        headers.put("Accept","application/json");
        response=APIUtils.apiDelete("/api/customers/"+customerId,headers);
    }



    @Then("user validates that customer and account is deleted with GET call")
    public void user_validates_that_customer_and_account_is_deleted_with_GET_call() {
        Map<String, Object> headers= new HashMap<>();
        headers.put("Accept","application/json");

        response=APIUtils.apiGet("/api/accounts/"+accountId,headers);

        response=APIUtils.apiGet("/api/customers/"+customerId,headers);
    }



    @Then("user validates the response message {string}")
    public void user_validates_the_response_message(String expectedMessage) {
        String actualErrorMsg=response.body().jsonPath().getString("message");
        Assert.assertEquals(expectedMessage+customerId,actualErrorMsg);
    }


    @When("user creates a transaction for created account and customer with POST call")
    public void userCreatesATransactionForCreatedAccountAndCustomerWithPOSTCall() {



        Map<String, Object> headers= new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");

        response=APIUtils.apiPost("/api/transaction",headers,"{\n" +
                "  \"account\": {\n" +
                "    \"accountType\": \"Checking\",\n" +
                "    \"balance\": 50000,\n" +
                "    \"customer\": {\n" +
                "      \"accountOpenDate\": \" 2022-03-06\",\n" +
                "      \"accounts\": [\n" +
                "        null\n" +
                "      ],\n" +
                "      \"active\": true,\n" +
                "      \"address\": \"123 Good Rd\",\n" +
                "      \"fullName\": \"Patel Harsh jr.\",\n" +
                "      \"id\": "+customerId+",\n" +
                "      \"isActive\": true\n" +
                "    },\n" +
                "    \"id\": "+accountId+",\n" +
                "    \"transactions\": [\n" +
                "      null\n" +
                "    ]\n" +
                "  },\n" +
                "  \"amount\": 1000,\n" +
                "  \"date\": \""+LocalDateTime.now()+"\",\n" +
                "  \"transactionId\":\"0\", \n" +
                "  \"transactionName\": \"Money for Home\"\n" +
                "}");


    }

    @When("user validates that transaction is created for a customer's account with GET customer call")
    public void userValidatesThatTransactionIsCreatedForACustomerSAccountWithGETCustomerCall() {

        Map<String, Object> headers= new HashMap<>();
        headers.put("Accept","application/json");

        response=APIUtils.apiGet("/api/customers/"+customerId,headers);

        String transactionName=response.body().jsonPath().getString("accounts[0].transactions[0].transactionName");



        Assert.assertEquals("Money for Home",transactionName);



    }

    @When("user deletes transaction, account and customer")
    public void userDeletesTransactionAccountAndCustomer() {

        transactionId=response.body().jsonPath().getString("accounts[0].transactions[0].transactionId");

        Map<String, Object> headers= new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");

        response=APIUtils.apiDelete("/api/transactions/"+transactionId,headers);
        response=APIUtils.apiDelete("/api/accounts/"+accountId,headers);
        response=APIUtils.apiDelete("/api/customers/"+customerId,headers);

    }

    @When("user validates account, customer and transaction are deleted with GET customer call")
    public void userValidatesAccountCustomerAndTransactionAreDeletedWithGETCustomerCall() {

        Map<String, Object> headers= new HashMap<>();
        headers.put("Accept","application/json");



        response= APIUtils.apiGet("/api/transactions/"+transactionId,headers);

        response=APIUtils.apiGet("/api/accounts/"+accountId,headers);

        response=APIUtils.apiGet("/api/customers/"+customerId,headers);



    }
}

