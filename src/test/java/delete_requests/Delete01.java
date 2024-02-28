package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01 extends JsonPlaceHolderBaseUrl {

    /*
    Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01(){

        //Set the url
        spec.pathParams("first","todos","second",198);

        //Set the expected data
        //JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(null,null,null); //doesn't give the expected empty body
        Map<String, Object> expectedData = jsonPlaceHolderMapper(null,null,null); // for assertion purposes
        System.out.println("expectedData = " + expectedData); //in console => expectedData = {}

        //Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();
        // in console ----v
        // {
        //
        //}

        //Do assertion
        assertEquals(200, response.statusCode());
        //response.as(HashMap.class); //De-Serialization
        Map<String,Object>actualData = convertJsonToJava(response.asString(), HashMap.class); //industry way
        //1st way:
        assertEquals(null,actualData.get(0));

        //2nd way:
        assertTrue(actualData.isEmpty());

        //3rd way:
        assertEquals(expectedData,actualData);

        //Anyone of the assertion way is fine, you don't need to do all of them

    }

}
