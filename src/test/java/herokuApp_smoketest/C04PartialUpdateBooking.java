package herokuApp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import java.util.Map;

import static herokuApp_smoketest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04PartialUpdateBooking extends HerokuAppBaseUrl {

            /*
                 Given
                     https://restful-booker.herokuapp.com/booking/{{bookingid}}
                And
                 {
                        "firstname" : "Jane",
                        "additionalneeds": "Extra pillows please"
                   }
                When
                  User sends PATCH request
         Then
             Status code is 200
          And
             Response body is like: {
                            "firstname": "Jane",
                            "lastname": "Brown",
                            "totalprice": 111,
                            "depositpaid": false,
                            "bookingdates": {
                                "checkin": "2024-01-01",
                                "checkout": "2024-01-01"
                            },
                            "additionalneeds": "Extra pillows please"
                        }
     */

    @Test
    public void patch(){

        spec.pathParams("first","booking","second",bookingid);

        Map<String,String> bookingDatesMap =  bookingDatesMapper("2024-01-01","2024-01-01");

        Map<String, Object>payload = herokuAppMapper("Jane",null,null,null,null,"Extra pillows please");

        System.out.println("payload = " + payload);

        Response response = given(spec).when().body(payload).patch("{first}/{second}");
        response.prettyPrint();

        //Use POJO to create expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2024-01-01","2024-01-01");
        HerokuAppPojo expectedData = new HerokuAppPojo("Jane","Brown",111,false,bookingDates,"Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        HerokuAppPojo actualData = convertJsonToJava(response.asString(), HerokuAppPojo.class);

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
