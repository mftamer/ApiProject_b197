package practice;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.ReqresInTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Q08 extends ReqresBaseUrl {
      /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-11-22T06:40:22.293Z"
                                              }
     */
      @Test
      public void test(){
          // Set the URL
          spec.pathParams("first","users");

          // Set the expected data
          ReqresInTestData obj = new ReqresInTestData();
          Map<String,String> expectedData = obj.expectedDataReqres("morpheus", "leader");
          System.out.println(expectedData);

          // Send the request and get the response

          Response response = given().spec(spec).contentType(ContentType.JSON). body(expectedData). when().post("/{first}");
          response.prettyPrint();

          // Do assertion
          // We need expected data and actual data to do assertion

          Map<String, String> actualData = response.as(HashMap.class);
          System.out.println("actualData" + actualData);

          // soft assertion

          response.
                  then().
                  assertThat().
                  statusCode(201).
                  body("name",equalTo("morpheus"),
                          "job", equalTo("leader"));
          // or

          assertEquals(201, response.getStatusCode());
          assertEquals(expectedData.get("name"),actualData.get("name"));
          assertEquals(expectedData.get("job"),actualData.get("job"));

          // hard assertion

          assertEquals(201, response.statusCode());
          assertEquals(actualData.get("name"), expectedData.get("name"));
          assertEquals(actualData.get("job"), expectedData.get("job"));


      }
}