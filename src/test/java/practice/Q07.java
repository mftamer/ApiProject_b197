package practice;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Q07 extends JsonPlaceHolderBaseUrl {
    /*
            Given
            https://jsonplaceholder.typicode.com/users/1
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be "application/json"
		And
		    "name" is "Leanne Graham",
		And
		    "email" is "Sincere@april.biz"
        And
		    "city" is "Gwenborough"
		And
		    "lat" is "-37.3159"
        And
		    Company name  is "Romaguera-Crona"
     */
    @Test
    public void test(){

        spec.pathParams("first", "users", "second", 1);

        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("name",is("Leanne Graham"),
                        "email",is("Sincere@april.biz"),
                        "address.city", is("Gwenborough"),
                        "address.geo.lat", is("-37.3159"),
                        "company.name",is("Romaguera-Crona"));
    }
}