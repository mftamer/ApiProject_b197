package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static test_data.JsonPlaceHolderTestData.stringBody;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Get11ObjectMapper extends JsonPlaceHolderBaseUrl {
    /*
    Given
          https://jsonplaceholder.typicode.com/todos/198
      When
           I send GET Request to the URL
       Then
           Status code is 200
           And response body is like {
                                      "userId": 10,
                                      "id": 198,
                                      "title": "quis eius est sint explicabo",
                                      "completed": true
                                    }


{"userId": 10,"id": 198,"title": "quis eius est sint explicabo","completed": true}
     */

    @Test
    public void get11(){

        // Set the url
        spec.pathParams("first","todos","second",198);

        // Set the expected data
            //Setting expected data in DIFFERENT WAYS
            //1st way: method call for a map
        Map<String, Object> expectedData1 = jsonPlaceHolderMapper(10, "quis eius est sint explicabo", true);
        System.out.println("expectedData1 = " + expectedData1);

            //2nd way: Create an object from Pojo class
        JsonPlaceHolderPojo expectedData2 = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo", true);
        System.out.println("expectedData2 = " + expectedData2);

            //3rd way:Using ObjectMapper and Map
        //For this, we need to create expected Data in String
//        String expectedDataStr = "{\n" +
//                "                                      \"userId\": 10,\n" +
//                "                                      \"id\": 198,\n" +
//                "                                      \"title\": \"quis eius est sint explicabo\",\n" +
//                "                                      \"completed\": true\n" +
//                "                                    }\n";
//        System.out.println("expectedDataStr = " + expectedDataStr);

      //  String expectedDataStr ="{\"userId\": 10,\"id\": 198,\"title\": \"quis eius est sint explicabo\",\"completed\": true}";

        String expectedDataStr = stringBody(10,"quis eius est sint explicabo", true);

        System.out.println("expectedDataStr = " + expectedDataStr);

        Map<String, Object> expectedData3 = convertJsonToJava(expectedDataStr, HashMap.class);
        System.out.println("expectedData3 = " + expectedData3);

        //4th way: Using ObjectMapper and Pojo

        JsonPlaceHolderPojo expectedData4 = convertJsonToJava(expectedDataStr,JsonPlaceHolderPojo.class);
        System.out.println("expectedData4 = " + expectedData4);

        /*
        NOTE: We set the expected data in various ways to practice -- You can userWhichever method you prefer
        In the market, ObjectMapper with POJO is the most preferred method
         */

        // Send the request and get the response
        Response response = given(spec).when().body(expectedData4).get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        //De-Serialization
        //Using ObjectMapper and Pojo
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData4.getUserId(), actualData.getUserId());
        assertEquals(expectedData4.getTitle(),actualData.getTitle());
        assertEquals(expectedData4.getCompleted(),actualData.getCompleted());

    }

}
