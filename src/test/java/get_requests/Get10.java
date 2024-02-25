package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerokuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/41
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Josh",
                "lastname": "Allen",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "midnight snack"
            }
     */

    @Test
    public void get10(){

        // Set the url
        spec.pathParams("first","booking","second",41);

        // Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuAppPojo expectedData = new HerokuAppPojo("John","Smith",111,true, bookingDates, "Breakfast");

        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        //De-Serialization
        HerokuAppPojo actualData = response.as(HerokuAppPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());



    }
}
