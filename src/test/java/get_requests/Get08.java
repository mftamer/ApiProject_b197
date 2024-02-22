package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
      Given
             https://jsonplaceholder.typicode.com/todos
      When
           I send GET Request to the URL
      Then
           1)Status code is 200
           2)Print all ids greater than 190 on the console
             Assert that there are 10 ids greater than 190
           3)Print all userIds whose ids are less than 5 on the console
             Assert that the number of userIds whose ids are less than 5 is 4
           4)Print all titles whose ids are less than 5
             Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void get08(){

        // Set the url
            spec.pathParam("first","todos");

        // Set the expected data
        // Send the request and get the response
            Response response = given(spec).get("{first}");
           // response.prettyPrint(); //print the whole body

        // Do Assertions

//        1)Status code is 200
        response.then().statusCode(200);
        assertEquals(200, response.statusCode());

        //First extract the data out of response body using JsonPath()
        JsonPath jsonPath = response.jsonPath();

//        2)Print all ids greater than 190 on the console
        List<Integer> idList = jsonPath.getList("id");
        System.out.println("idList = " + idList);

//        Assert that there are 10 ids greater than 190

        //1st Way: Using list and loop
        List<Integer> idsGreaterThan190 = new ArrayList<>();
        for (int w : idList){
            if (w>190){
               // System.out.println(w);
                idsGreaterThan190.add(w);
            }
        }
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);
        assertEquals(10,idsGreaterThan190.size());

        System.out.println("********GROOVY LANGUAGE********");
        //2nd Way: GROOVY LANGUAGE
       // System.out.println(jsonPath.getList("findAll{it.id>190}.title")); //find all items whose id is greater than 190 and return their title

        List<Integer> idsGreaterThan190Groovy = jsonPath.getList("findAll{it.id>190}.id"); //it means items
        System.out.println("idsGreaterThan190Groovy = " + idsGreaterThan190Groovy);
        assertEquals(10, idsGreaterThan190Groovy.size());

//        3)Print all userIds whose ids are less than 5 on the console
        List<Integer> userIdsList = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIdsList = " + userIdsList);
       // assertEquals();

//        Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4,userIdsList.size());

//        4)Print all titles whose ids are less than 5
        List<Integer> titleList = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titleList = " + titleList);

//        Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titleList.contains("delectus aut autem"));










    }








}
