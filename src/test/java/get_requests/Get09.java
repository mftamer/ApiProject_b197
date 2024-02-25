package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Get09 extends HerokuAppBaseUrl {
/*
Given
        https://restful-booker.herokuapp.com/booking/51
    When
        I send GET Request to the url
    Then
        Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
 */

    @Test
    public void get09(){

        // Set the url
        spec.pathParams("first","booking","second",176);

        // Set the expected data
        //inner json
        Map<String,String> bookingMap = bookingDatesMapper("2018-01-01","2019-01-01");
        System.out.println("bookingMap = " + bookingMap);
        //outer json
        Map<String, Object>expectedData = herokuAppMapper("Josh","Allen", 111, true,bookingMap, "super bowls");
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}"); //Serialization
        response.prettyPrint();

        // Do Assertions

        Map<String,Object> actualData = response.as(HashMap.class);// De-Serialization
        System.out.println("actualData = " + actualData);

        // Do Assertions
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        //assertEquals(expectedData.get("checkin"),actualData.get("checkin")); //it worked no need for type casting
        //assertEquals(expectedData.get("bookingdates"),actualData.get("bookingdates"));
        //This is the type casting method below
        //assertEquals(((Map)(expectedData.get("bookingMap"))).get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));


//        //This is the concept of the type casting
//        Object object = new HashMap<>();
//        ((Map)object).get();

    }

}

// Set the url
// Set the expected data
// Send the request and get the response
// Do Assertions