package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerokuAppBaseUrl {

    /*
    Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
           Among the data there should be someone whose firstname is "John" and lastname is "Smith"
     */

    @Test
    public void get05(){


        // Set the url
        spec.pathParam("first", "booking").queryParams("firstname", "John", "lastname", "Smith");
//        https://restful-booker.herokuapp.com/booking?firstname=John&lastname=Smith

        // Set the expected data
        // Send the request and get the response
        Response response= given(spec).get("{first}");
        response.prettyPrint();

        // Do assertions
        response.then().statusCode(200).body("bookingid", hasSize(greaterThan(50)));

        assertTrue(response.asString().contains("bookingid"));


    }
}