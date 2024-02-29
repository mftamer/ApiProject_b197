package herokuApp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static herokuApp_smoketest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C06GetBookingAfterDeleting extends HerokuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/{{bookingid}}
         When
            User sends GET request
         Then
             Status code is 404
          And
             Response body is like: "Not Found"
     */

    @Test
    public void get(){

        spec.pathParams("first","booking","second",bookingid);

        String expectedData = "Not Found";

        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        String actualData = response.asString();

        assertEquals(404, response.statusCode());

        assertEquals(expectedData,actualData);

    }

}
