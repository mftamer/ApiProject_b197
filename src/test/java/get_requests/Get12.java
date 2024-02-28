package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get12 extends GoRestBaseUrl {

    /*
     Given
   https://gorest.co.in/public/v1/users
When
   User sends GET Request
Then
   The value of "pagination limit" is 10,
And
   The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
And
   The number of users should be 10
And
   We have at least one "active" status
And
   "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
And
   The female users are more than or equals to male users
     */

    @Test
    public void get12(){

        //Set the url
        spec.pathParam("first","users");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        //Do assertion
        //The value of "pagination limit" is 10,
        //response.then().statusCode(200).body("meta.pagination.limit", equalTo(10));
        //actual value first then expected value in the body method

        //We will use JsonPath since it is easier for assertion (for the future of code as well)
        JsonPath json = response.jsonPath();
        assertEquals(10,json.getInt("meta.pagination.limit"));

        //The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        String currentLink = "https://gorest.co.in/public/v1/users?page=1";
        assertEquals(currentLink, json.getString("meta.pagination.links.current"));

        //The number of users should be 10
        int numOfUsers = 10;
//        List<Integer>actualUsers = json.getList("data.id");
//        actualUsers.size();
        //OR
        int actualUsers = json.getList("data.id").size();
        assertEquals(numOfUsers,actualUsers);

        //We have at least one "active" status
        Boolean isExist = json.getList("data.status").contains("active");
        assertTrue(isExist);

        //"Aasha Ahuja DO, Aagney Devar, Anandamayi Varman" are among the users
        //The names are keep changing, therefore, we took the first 3 names from the list
        List<String> userNameList = json.getList("data.name");
        System.out.println("userNameList = " + userNameList);
        assertTrue(userNameList.contains("Aasha Ahuja DO"));
        assertTrue(userNameList.contains("Aagney Devar"));
        assertTrue(userNameList.contains("Anandamayi Varman"));

        //The female users are more than or equals to male users
        //In Groovy Language
        int numOfMaleUsers = json.getList("data.findAll{it.gender=='male'}.name").size();
        //int numOfFemaleUsers = json.getList("findAll{it.gender=='female'}.name").size();
        //assertTrue(numOfFemaleUsers >= numOfMaleUsers);
        //OR
        int numOfFemaleUsers = actualUsers-numOfMaleUsers;
        assertTrue(numOfFemaleUsers >= numOfMaleUsers);

        //OR
        /*
        //        The female users are more than or equals to male users
         List<String>  maleList = json.getList("data.findAll{it.gender == 'male'}");
         int maleUsers = maleList.size();
        System.out.println("maleList = " + maleList);
        int femaleList = actualUsers-maleList.size();
          assertTrue(femaleList >= maleUsers);

//        assertTrue(femaleList >= maleList.size());
         */

    }

}
//Set the url
//Set the expected data
//Send the request and get the response
//Do assertion