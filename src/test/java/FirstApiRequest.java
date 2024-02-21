import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FirstApiRequest {

    public static void main(String[] args) {

        /*
        To make API requests, we need to add Rest Assured dependency tp pom.xml file
          i) Status code
          ii) Status line
          iii) ContentType
          iv) Response time
          v) Headers

         */

        //        https://petstore.swagger.io/v2/pet/4560

        System.out.println(given().when().get("https://petstore.swagger.io/v2/pet/4560")); // provides reference

//        given().when().get("https://petstore.swagger.io/v2/pet/4560").prettyPrint();  // prints the actual body of the response


        Response response = given().when().get("https://petstore.swagger.io/v2/pet/4560");
        response.prettyPrint();

        // How to get Status code

//        System.out.println(given().when().get("https://petstore.swagger.io/v2/pet/4560").statusCode());

        System.out.println("Status Code = " + response.statusCode());

        // How to get Status Line
        System.out.println("Status Line = " + response.statusLine());

        // How to get Content Type
        System.out.println("Content Type = " + response.contentType());

        // How to get response time
        System.out.println("Response Time = " + response.time());

        // How to get headers

        System.out.println("Response Headers = " + response.headers());

        System.out.println("***** Response headers one by one *****");
        System.out.println("Response date: "+response.header("Date"));

        System.out.println("Response Server = " + response.header("Server"));


    }

}