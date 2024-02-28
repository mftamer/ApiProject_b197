package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp(){
        String baserUrl ="https://gorest.co.in/public/v1/";

        spec = new RequestSpecBuilder().setBaseUri(baserUrl).setContentType(ContentType.JSON).build();
    }
}
