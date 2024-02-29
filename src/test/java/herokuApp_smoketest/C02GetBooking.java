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

public class C02GetBooking extends HerokuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/{{bookingid}}
    When
        User send GET request
    Then
        Status code is 200
    And
        Response body is like:
                                    {
                                "firstname": "Jim",
                                "lastname": "Brown",
                                "totalprice": 111,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                                },
                                "additionalneeds": "Breakfast"
                                     }
     */

    @Test
    public void get(){

        spec.pathParams("first","booking","second",bookingid);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuAppPojo expectedData = new HerokuAppPojo("Jim","Brown",111,true,bookingDates,"Breakfast");

        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());

        HerokuAppPojo actualData = convertJsonToJava(response.asString(),HerokuAppPojo.class);
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());






    }







}
