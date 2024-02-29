package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerokuApp {

    public static void main(String[] args) {
        generateToken();
        System.out.println("token = " + generateToken());
        //this is just to check if the token is generated or not
    }
    public static String generateToken(){
        String credentials = "{\"username\" : \"admin\", \"password\" : \"password123\"}";

        Response response = given().when().body(credentials).contentType(ContentType.JSON).post("https://restful-booker.herokuapp.com/auth");

        return response.jsonPath().getString("token");

    }


}
//    {"username": "admin", "password" : "password123"}