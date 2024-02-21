package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
    1. We use Postman for Manual Tests of API
    2. We use Rest Assured Library for automation of API
    3. To type the automation script, follow these steps:
        a) We need to understand the requirement
        b) Write your test scrips in Gherkin Language
            Which has four keywords:
           i) Given: used for pre-conditions (url, authorization, body, content type etc.)
           ii) When: used for action (method name => get, post, put, delete etc.)
           iii) Then: used for assertions
           iv) And: used for repeating any step (multiple use of keywords)
         c) Start writing your Automation script
            1. Set the URL
            2. Set the expected data
            3. Send the request and get response
            4. Do Assertion
     */

    @Test
    public void get01(){
        /*
        Given
               https://restful-booker.herokuapp.com/booking/10
            When a
               User sends a GET Request to the url
           Then
               HTTP Status Code should be 200
           And
               Content Type should be application/json
           And
               Status Line should be HTTP/1.1 200 OK
         */


//        1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10";

//        2. Set the expected data -- do it later

//        3. Send the request and get the response

//        given().when().get(url).prettyPrint();  // when() method is OPTIONAL

        Response response = given().get(url);
        response.prettyPrint(); // prints the response on the console

//        4. Do Assertion
        response.then().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // OR

        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
        // we can also use ContentType.JSON instead of this String "application/json"
        // because ContentType.JSON is declared as a CONSTANT VALUE and constant values are saved in 'enum' (another structure in Java like class and interface)


    }

}