package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post07ObjectMapperMap extends JsonPlaceHolderBaseUrl {

    /*
    Given
 1) https://jsonplaceholder.typicode.com/todos
 2) {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
     }
  I send POST Request to the Url
Then
  Status code is 201
And
  response body is like {
                          "userId": 55,
                          "title": "Tidy your room",
                          "completed": false,
                          "id": 201
                          }
     */

@Test
    public void post07() throws JsonProcessingException {

    // Set the url
    spec.pathParam("first","todos");

    // Set the expected data
    //Method call to create the expected data as a map
    Map<String,Object> payload = jsonPlaceHolderMapper(55,"Tidy your room",false);
    System.out.println("payload = " + payload);

    // Send the request and get the response
    Response response = given(spec).when().body(payload).post("{first}");
    response.prettyPrint();

    // Do Assertions
    //De-Serialization
    //1st Way:
//    System.out.println("response.asString() = " + response.asString());
//
//    //2nd Way:
//    JsonPath jsonPath = response.jsonPath();
//    System.out.println("response.jsonPath() = " + jsonPath.getString("title"));
//
//    //3rd Way:
//    System.out.println("response.as(HashMap.class) = " + response.as(HashMap.class));
//
//    //4th Way:
//    System.out.println("response.as(JsonPlaceHolderPojo.class) = " + response.as(JsonPlaceHolderPojo.class));

    //5th Way: ObjectMapper with Map
//    ObjectMapper objectMapper = new ObjectMapper();
//    objectMapper.readValue(response.asString(), HashMap.class);
    //OR
    Map<String,Object> actualData = new ObjectMapper().readValue(response.asString(),HashMap.class);

    assertEquals(201,response.statusCode());
    assertEquals(payload.get("userId"), actualData.get("userId"));
    assertEquals(payload.get("title"), actualData.get("title"));
    assertEquals(payload.get("completed"), actualData.get("completed"));

}

}
// Set the url
// Set the expected data
// Send the request and get the response
// Do Assertions