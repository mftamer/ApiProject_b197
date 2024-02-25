package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerokuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/99
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                        {
                            "firstname": "John",
                            "lastname": "Smith",
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
    public void get06(){
        //Set the url
        spec.pathParams("first","booking", "second", 55);
        //if it gives error 404, go to postman app and check for the John Smith (devs can remove or change it)

        //Set the expected data ... do it later
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}"); //syntax for more than one path param =>"{}/{}"
        response.prettyPrint();

        //Do assertion
        // 1st way: using matchers (equalTo() method or is() method)
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("John"),
                        "lastname",equalTo("Smith"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"), //this is for nested JSON
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));

        //using is() method
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",is("John"),
                        "lastname",is("Smith"),
                        "totalprice", is(111),
                        "depositpaid", is(true),
                        "bookingdates.checkin", is("2018-01-01"),
                        "bookingdates.checkout", is("2019-01-01"),
                        "additionalneeds",is("Breakfast"));

        /*
        NOTE: We have multiple ways of extracting the data out of response body:
                i) asString() method => changes data type of response to String
                ii) JsonPath
                iii) Maps
                iv) Pojo Class
         */

        //2nd Way:Using JsonPath
        JsonPath jsonPath = response.jsonPath();
//        String firstName = jsonPath.getString("firstname");
//        System.out.println("firstName = " + firstName);
//        boolean depositpaid = jsonPath.getBoolean("depositpaid");
//        System.out.println("depositpaid = " + depositpaid);

        assertEquals("John", jsonPath.getString("firstname"));
        assertEquals("Smith", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertEquals(true, jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

        //Soft-assertion in TestNG framework


        // Set the url
        // Set the expected data
        // Send the request and get the response
        // Do Assertions

    }

}
