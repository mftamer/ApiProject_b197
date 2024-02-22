package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppBaseUrl {

    // This class is created to prevent repetition of pre-conditions (url, content type, header, token etc.)

    protected RequestSpecification spec;

    @Before  // This annotation will run before each test method
    public void setUp() {

        String baseUrl = "https://restful-booker.herokuapp.com";
        spec = new RequestSpecBuilder().setBaseUri(baseUrl).build();

    }


}