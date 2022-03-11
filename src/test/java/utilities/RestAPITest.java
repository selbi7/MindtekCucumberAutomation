package utilities;

import io.restassured.response.Response;
import org.postgresql.gss.GSSOutputStream;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestAPITest {

    public static void main(String[] args) {

        /*
        Get list of books
        1.Provide URL
        2.Provide Headers-application/json

         */
       //RestAssured,given--another way

        Response response=
        given().baseUri("https://simple-books-api.glitch.me")
                .and().header("Accept","application/json")
                .when().get("/books");

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        /*
        Create an order
        1.URL
        2.Headers
        3.Body
         */

        Random random= new Random();
        int randomNum=random.nextInt();


        Response authResponse=given().baseUri("https://simple-books-api.glitch.me")
                .and().header("Content-Type","application/json")
                .and().header("Accept","application/json")
                .and().body("{\n" +
                        "   \"clientName\": \"Selbi\",\n" +
                        "   \"clientEmail\": \"testers"+randomNum+"@example.com\"\n" +
                        "}")
                .when().post("/api-clients/");
        System.out.println(authResponse.getStatusCode());
        System.out.println(authResponse.getBody().asString());

        String token= authResponse.body().jsonPath().getString("accessToken");

        Response postResponse=
                given().baseUri("https://simple-books-api.glitch.me")
                        .and().header("Content-Type","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .and().body("{\n" +
                                "  \"bookId\": 1,\n" +
                                "  \"customerName\": \"Patel Harsh\"\n" +
                                "}")
                        .when().post("/orders");
        System.out.println(postResponse.getStatusCode());


    }


}
