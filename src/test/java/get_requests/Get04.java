package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {
    /*
    Given
                 https://jsonplaceholder.typicode.com/todos
             And
                 Accept type is "application/json"
             When
                  I send a GET request to the Url
             Then
                 HTTP Status Code should be 200
             And
                 Response format should be "application/json"
             And
                 There should be 200 todos
             And
                 "quis eius est sint explicabo" should be one of the todos title
             And
                 2, 7, and 9 should be among the userIds
     */


    @Test
    public void get04(){

        // Set the url
//        String url = "https://jsonplaceholder.typicode.com/todos"; // not recommended
//       We set the baseurl as precondition in Specification class under base_urls package
//        and extend this class to have access to those specifications

        spec.accept(ContentType.JSON).pathParam("first", "todos" );

        // Set the expected data ... do it later
        // Send the request and get the response
       // given().spec(spec).when().get("{first}");
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();


        // Do Assertions
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200),
                        //                body("[0].title", equalTo("delectus aut autem")).  // You can get the json value from a list of json by using index => "[0].title"
                        "title", hasItem("quis eius est sint explicabo"),
                        "userId", hasItems(2,7,9));


        /*
            If response body returns collection (list):
                i) check its size using hasSize() method
                ii) check if any item exists in that list using hasItem() method
                iii) check multiple items by using hasItems() method
        */




    }
}