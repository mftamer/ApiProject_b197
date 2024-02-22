package get_requests;

import base_urls.PetstoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.assertEquals;

public class Get07 extends PetstoreBaseUrl {

    /*
    Given
        https://petstore.swagger.io/v2/pet/1889
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
               {
                "id": 1889,
                "category": {
                    "id": 0,
                    "name": "Bird"
                },
                "name": "Tweety",
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 0,
                        "name": "Pets"
                    },
                    {
                        "id": 0,
                        "name": "PrettyPaws"
                    }
                ],
                "status": "pending"
                }
     */

    @Test
    public void get07(){

//        1. Set the URL
        spec.pathParams("first","pet",
                "second","1889");

//        2. Set the expected data -- do it later

//        3. Send the request and get the response

        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();


//        4. Do Assertion
        response.then().statusCode(200).contentType(ContentType.JSON);

        //HOMEWORK
        //1st Way: Soft Assertion (using single body() method)

        //2nd Way: Using Jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertEquals(1889, jsonPath.getInt("id"));
        assertEquals("Bird", jsonPath.getString("category.name"));
        assertEquals(0, jsonPath.getInt("category.id"));
        assertEquals("Tweety", jsonPath.getString("name"));
        assertEquals("string", jsonPath.getString("photoUrls[0]"));
        assertEquals("Pets", jsonPath.getString("tags[0].name"));
        assertEquals(0, jsonPath.getInt("tags[0].id"));
        assertEquals("PrettyPaws", jsonPath.getString("tags[1].name"));
        assertEquals(0, jsonPath.getInt("tags[1].id"));
        assertEquals("pending", jsonPath.getString("status"));








    }

}
