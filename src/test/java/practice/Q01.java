package practice;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

    public class Q01 extends ReqresBaseUrl {

    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

        @Test
        public void test(){

            // Set the URL
            spec.pathParams("first", "users", "second", 3);

            // Set the expected data


            // Send the request and get the response
            Response response = given().spec(spec).when().get("/{first}/{second}");
            response.prettyPrint();

            // Do assertions
//        assertEquals(200, response.statusCode());
//        assertEquals("application/json", response.contentType());
//        assertEquals("HTTP/1.1 200 OK", response.statusLine());

            response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    statusLine("HTTP/1.1 200 OK");


        }



    }