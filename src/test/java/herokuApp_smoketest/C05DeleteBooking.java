package herokuApp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuApp_smoketest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class C05DeleteBooking extends HerokuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/{{bookingid}}
     When
        User sends DELETE request
    Then
        Status code is 201
    And
        Response body is like:
                                Created
     */

    @Test
    public void delete(){

        spec.pathParams("first","booking","second", bookingid);

        String expectedData = "Created";

        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        response.then().statusCode(201);

        //1st way:
        String actualData = response.asString();
        assertEquals(expectedData,actualData);

    }

}
