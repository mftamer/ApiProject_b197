package herokuApp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static herokuApp_smoketest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;

public class C03UpdateBooking extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/{{bookingid}}
    And
        body;
                        {
                    "firstname" : "James",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : false,
                    "bookingdates" : {
                        "checkin" : "2024-01-01",
                        "checkout" : "2024-01-01"
                    },
                    "additionalneeds" : "Lunch"
                        }
     When
        User send PUT request
    Then
        Status code is 200
    And
        Response body is like:
                            {
                        "firstname": "James",
                        "lastname": "Brown",
                        "totalprice": 111,
                        "depositpaid": false,
                        "bookingdates": {
                            "checkin": "2024-01-01",
                            "checkout": "2024-01-01"
                        },
                        "additionalneeds": "Lunch"
        }
     */

    @Test
    public void put(){

        spec.pathParams("first","booking","second", bookingid);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2024-01-01","2024-01-01");
        HerokuAppPojo payload = new HerokuAppPojo("James","Brown",111, false,bookingDates,"Lunch");

        System.out.println("payload = " + payload);

        //Response response = given(spec).header("Cookie","token=79e2e74a1e81036").when().body(payload).put("{first}/{second}");
        //Note: Add the token in the header in the baseUrl class
        Response response = given(spec).when().body(payload).put("{first}/{second}");
        response.prettyPrint();


    }

}
