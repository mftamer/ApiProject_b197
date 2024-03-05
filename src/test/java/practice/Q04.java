package practice;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuAppPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q04 extends HerokuAppBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"
    */

    @Test
    public void test(){
        // Set the URL

        spec.pathParam("first", "booking").
                queryParams("firstname","Brandon", "lastname","Wilson");


        // Set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Do assertion

        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));


    }


}