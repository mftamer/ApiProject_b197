package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationGMIBank {

    public static void main(String[] args) {
        generateToken();
        System.out.println("Bearer " + generateToken());
        //this is just to check if the token is generated or not
    }
    public static String generateToken(){
        String credentials = "{\n" +
                "    \"password\": \"Mark.123\",\n" +
                "    \"rememberMe\": true,\n" +
                "    \"username\": \"mark_twain\"\n" +
                "}";

        Response response =
                given().
                when().
                body(credentials).
                        contentType(ContentType.JSON).
                        post("https://gmibank.com/api/authenticate");

        return response.jsonPath().getString("id_token");

    }


}
//   {"password": "Mark.123", "rememberMe": true,"username": "mark_twain"}