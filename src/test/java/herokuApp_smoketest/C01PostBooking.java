package herokuApp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01PostBooking extends HerokuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
        }
    When
        User sends POST request
    Then
        Status code should be 200
    And
        Body should be like:
                         {
                            "bookingid": 4100,
                            "booking": {
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
                         }

     */

    public static int bookingid; //to make it accessible from other classes

    @Test
    public void post(){

        spec.pathParam("first","booking");

        BookingDatesPojo bookingMap = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuAppPojo payload = new HerokuAppPojo("Jim", "Brown", 111, true,bookingMap, "Breakfast");
       // System.out.println("payload = " + payload); //it is just to see

        Response response = given(spec).when().body(payload).post("{first}");
        response.prettyPrint();

        //response.as(HerokuAppPojo.class);
        HerokuResponsePojo actualData = convertJsonToJava(response.asString(), HerokuResponsePojo.class);

        //Assertion:

        assertEquals(200,response.statusCode());
        assertEquals(payload.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payload.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payload.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payload.getDepositpaid(),actualData.getBooking().getDepositpaid());
      //  assertEquals(payload.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
      //  assertEquals(payload.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        //OR (WITH MAP)
        assertEquals(bookingMap.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingMap.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

        assertEquals(payload.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        bookingid = response.jsonPath().getInt("bookingid");
        System.out.println("bookingid = " + bookingid);
    }

}
