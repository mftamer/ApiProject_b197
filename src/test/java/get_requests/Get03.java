package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 {
    /*
    Given
                https://jsonplaceholder.typicode.com/todos/23
             When a
                User sends GET Request to the URL
            Then
                HTTP Status Code should be 200
            And
                Response formats should be “application/json”
            And
                “title” is “et itaque necessitatibus maxime molestiae qui quas velit,”
            And
                “completed” is false
            And
                “userId” is 2
     */

    // In the previous get request, we mainly asserted the metadata (headers, outside the body)
    // In this get request, we will assert metadata as well as the body of the response and its values

    @Test
    public void get03(){
        // Set the url
        String url = "https://jsonplaceholder.typicode.com/todos/23";
        // Set the expected data --- will do later

        // Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

        // Do assertions

        // 1st way: Hard Assertion
        // If you want your test to stop execution when it encounters an error, use Hard Assertion
        // Use separate body() methods for Hard Assertion
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));


        // 2nd way: Soft Assertion
        // If you want your test to continue execution even after it finds an error, use Soft Assertion
        // Use single body() method for Soft Assertion
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false), "userId", equalTo(2));

        // Note:

        // Hard Assertion stops the code/ system straightaway as soon as it encounters any error
        // Soft Assertion does NOT stop the code/ system straightaway, it allows running all codes and print the error message in the end
        // Which one is better?
        // Depends on test type and test level



    }

}
