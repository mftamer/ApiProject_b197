package practice;

import base_urls.PetstoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.PetStoreTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q09 extends PetstoreBaseUrl {
     /*
    Type automation code to create a 'user' by using "https://petstore.swagger.io/"" documentation.
    Given
        https://petstore.swagger.io/v2/user/createWithList
    And
            [
              {
                "id": 134,
                "username": "Swag",
                "firstName": "Allen",
                "lastName": "Sugar",
                "email": "as@gmail.com",
                "password": "the_legend?",
                "phone": "0123456789",
                "userStatus": 82
              }
            ]
     When I send POST Request to the Url
     Then
            Status code is 200
     And
           response body should be like {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "ok"
                                             }
    */
     @Test
     public void test(){
         // Set the URL
         spec.pathParams("first", "v2", "second","user");

         // Set the expected data

         PetStoreTestData user = new PetStoreTestData();
         Map<String, Object> payload = user.expectedDataUserSetUp(134, "Swag", "Allen","Sugar", "as@gmail.com", "the_legend?", "0123456789", 82);

         // Send the request and get the response
         Response response = given(). spec(spec).contentType(ContentType.JSON).body(payload).when().post("/{first}/{second}");
         response.prettyPrint();

         // Do assertion

         Map<String ,Object> actualData =response.as(HashMap.class);
         assertEquals(200,response.statusCode());
         assertEquals(200,actualData.get("code"));
         assertEquals("unknown",actualData.get("type"));


     }

}