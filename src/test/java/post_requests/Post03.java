package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post03 extends JsonPlaceHolderBaseUrl {

    @Test
    public void post03(){
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)  {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
         }
      3) Content type should be json
   When
       I send a POST Request to the Url

   Then
       Status code is 201
   And
       response body is like
                    {
                       "userId": 55,
                       "title": "Tidy your room",
                       "completed": false,
                       "id": 201
                     }

NOTE: for payload {"userId": 55, "title": "Tidy your room", "completed": false}
     */
        // Set the url
        spec.pathParam("first", "todos");

        // Set the expected data (payload => the data that you want to transfer to the server)
        // We can set the data using MAP => RECOMMENDED

        Map<String, Object> payload = jsonPlaceHolderMapper(55,"Tidy your room",false);

        // Send the request and get the response
        Response response = given(spec).when().body(payload).post("{first}"); // At this point, we need to do Serialization
        response.prettyPrint();

        // Do Assertions

        Map<String, Object> actualData = response.as(HashMap.class); // De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(payload.get("userId"), actualData.get("userId"));
        assertEquals(payload.get("title"), actualData.get("title") );
        assertEquals(payload.get("completed"), actualData.get("completed"));

        // We didn't use any hard code in this test method


    }

}