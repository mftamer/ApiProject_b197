package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Post04 extends HerokuAppBaseUrl {
/*
Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 999,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2024-01-01",
                "checkout" : "2024-01-01"
            },
            "additionalneeds" : "Extra pillows please"
        }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                        "bookingid": 4809,
                                        "booking": {
                                            "firstname": "Jim",
                                            "lastname": "Brown",
                                            "totalprice": 999,
                                            "depositpaid": true,
                                            "bookingdates": {
                                                "checkin": "2024-01-01",
                                                "checkout": "2024-01-01"
                                            },
                                            "additionalneeds": "Extra pillows please"
                                        }
                                    }
 */

        @Test
            public void post04(){

            // Set the url
            spec.pathParam("first","booking");

            // Set the expected data
            //inner json
            Map<String,String >bookingMap = bookingDatesMapper("2024-01-01","2024-01-01");
            System.out.println("bookingMap = " + bookingMap);

            //Outer json
            Map<String,Object>payload = herokuAppMapper("Jim","Brown",999, true,bookingMap,"Extra pillows please");
            System.out.println("payload = " + payload);

            // Send the request and get the response
            Response response = given(spec).when().body(payload).post("{first}");
            response.prettyPrint();

            // Do Assertions
            //1 st way: body assertions
            response.
                    then().
                    statusCode(200).
                    body("booking.firstname",equalTo(payload.get("firstname")),
                    "booking.lastname",equalTo(payload.get("lastname")),
                    "booking.totalprice",equalTo(payload.get("totalprice")),
                    "booking.depositpaid",equalTo(payload.get("depositpaid")),
                    "booking.bookingdates.checkin",equalTo(bookingMap.get("checkin")),
                    "booking.bookingdates.checkout",equalTo(bookingMap.get("checkout")),
                    "booking.additionalneeds",equalTo(payload.get("additionalneeds"))
                            );

            //HOMEWORK
            //2nd: using assertEqual() and jsonPath()
            //3rd way: using maps => you will have to do type casting
            //4th way: pojo class




        }









}
