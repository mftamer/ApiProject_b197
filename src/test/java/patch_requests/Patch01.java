package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos/72
      2)  {
             "title": "Read your storybook"
            }
      3) Content type should be json
   When
       I send PUT Request to the Url

   Then
       Status code is 200
   And
       response body is like
                    {
                     "userId": 4,
                     "id": 72,
                     "title": "Wash the dishes",
                    "completed": false
                    }
     */

    @Test
    public void patch01(){

        // Set the url
        spec.pathParams("first","todos","second","72");

        // Set the expected data
        Map<String, Object> payload = jsonPlaceHolderMapper(null,"Read your storybook",null );
        System.out.println("payload = " + payload);

        // Send the request and get the response
        Response response = given(spec).when().body(payload).patch("{first}/{second}"); //Serialization
        response.prettyPrint();

        // Do Assertions
        Map<String , Object> actualData = response.as(HashMap.class); //De-Serialization
        System.out.println("actualData = " + actualData);

        //1st way to resolve the issue of payload (which has only title and its value)
//        payload.put("userId",4);
//        payload.put("completed",false);
//        System.out.println("payload after sending API requests= " + payload);
//        assertEquals(200, response.statusCode());
//        assertEquals(payload.get("userId"), actualData.get("userId"));
//        assertEquals(payload.get("title"), actualData.get("title"));
//        assertEquals(payload.get("completed"), actualData.get("completed"));

        //2nd way: Create a separate map for assertion
        Map<String, Object> expectedData = jsonPlaceHolderMapper(4,"Read your storybook",false);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }



}