package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post08ObjectMapperPojo extends JsonPlaceHolderBaseUrl {

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
    public void post08() throws JsonProcessingException {
        // Set the url
        spec.pathParam("first", "todos");

        // Set the expected data
        // Use Pojo class object to create the expected data
        JsonPlaceHolderPojo payload = new JsonPlaceHolderPojo(55, "Tidy your room", false);
        System.out.println("paylaod = " + payload);

        // Send the request and get the response
        Response response = given(spec).when().body(payload).post("{first}"); // Serialisation
        response.prettyPrint();

        // Do assertions
        // De-Serialisation
        // 1st way:
//        System.out.println("response.asString() = " + response.asString());
//
//        // 2nd way:
//        JsonPath jsonPath = response.jsonPath();
//        System.out.println("response.jsonPath() = " + jsonPath.getString("title"));
//
//        // 3rd way:
//        System.out.println("response.as(HashMap.class) = " + response.as(HashMap.class));
//
//        // 4th way:
//
//        System.out.println("response.as(JsonPlaceHolderPojo.class) = " + response.as(JsonPlaceHolderPojo.class));

        // 5th way: ObjectMapper with POJO

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.readValue(response.asString(), JsonPlaceHolderPojo.class);
        JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);


        assertEquals(201, response.statusCode());
        assertEquals(payload.getUserId(), actualData.getUserId());
        assertEquals(payload.getTitle(), actualData.getTitle());
        assertEquals(payload.getCompleted(), actualData.getCompleted());

    }

}