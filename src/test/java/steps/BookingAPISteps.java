package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.bookingapipojo.Booking;
import pojos.bookingapipojo.BookingDates;
import utilities.ConfigReader;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BookingAPISteps {
    Response response;

    Map<String,Object> data;

    String bookingId;

    @Given("user sends create booking api POST call with data")
    public void user_sends_create_booking_api_POST_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        data=dataTable.asMap(String.class,Object.class);
        Booking booking= new Booking();
        booking.setFirstname(data.get("firstName").toString());
        booking.setLastname(data.get("lastName").toString());
        booking.setTotalprice(Integer.valueOf(data.get("totalPrice").toString()));
        booking.setDepositpaid(Boolean.valueOf(data.get("depositPaid").toString()));

        BookingDates bookingDates= new BookingDates();

        bookingDates.setCheckin(data.get("checkIn").toString());
        bookingDates.setCheckout(data.get("checkOut").toString());

//        bookingDates.setCheckin(LocalDate.parse(data.get("checkIn").toString()));
//        bookingDates.setCheckout(LocalDate.parse(data.get("checkOut").toString()));

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds(data.get("additionalNeeds").toString());


        response=
                given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Accept","application/json")
                        .and().body(booking)   //pojo class
                        .when().post("/booking");
        System.out.println(response.body().asString());
        bookingId=response.body().jsonPath().getString("bookingid");
    }

    @Then("user validates status code {int} fro booking")
    public void user_validates_status_code_fro_booking(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);

    }

    @Then("user validates that booking is created with api GET call")
    public void user_validates_that_booking_is_created_with_api_GET_call() {
        response=
                given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/booking/"+bookingId);

        System.out.println(response.body().asString());

        Booking responseBody=response.body().as(Booking.class);

        Assert.assertEquals(data.get("firstName").toString(),responseBody.getFirstname());
        Assert.assertEquals(data.get("lastName"),responseBody.getLastname());
        Assert.assertEquals(data.get("totalPrice"),responseBody.getTotalprice().toString());
        Assert.assertTrue(responseBody.getDepositpaid());
        Assert.assertEquals(data.get("checkIn"),responseBody.getBookingdates().getCheckin());
        Assert.assertEquals(data.get("checkOut"),responseBody.getBookingdates().getCheckout());

    }

    @When("user updates booking with api PATCH call with data")
    public void user_updates_booking_with_api_PATCH_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String,Object> updateData = dataTable.asMap(String.class,Object.class);

        response=
        given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                .and().header("Content-Type","application/json")
                .and().header("Accept","application/json")
                .and().header("Authorization","basic")
                .and().header("Cookie","token="+getToken())
                .and().body(updateData)
                .when().patch("/booking/"+bookingId);
        System.out.println(response.body().asString());
    }



    @When("user deletes booking with api DELETE call")
    public void user_deletes_booking_with_api_DELETE_call() {
        response=
        given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                .and().header("Authorization","basic")
                .and().header("Cookie","token="+getToken())
                .when().delete("/booking/"+bookingId);

    }


    @Then("user validates booking is deleted and GET call has {int} status code")
    public void user_validates_booking_is_deleted_and_GET_call_has_status_code(Integer expectedStatusCode) {
        response=
                given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                        .and().header("Accept","application/json")
                        .when().get("/booking/"+bookingId);
        response.then().statusCode(expectedStatusCode);
    }

    public String getToken () {
        return given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                .and().header("Content-Type","application/json")
                .and().body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}") //as POJO, String, Map;
                .when().post("/auth")
                .body().jsonPath().getString("token");
    }


}
