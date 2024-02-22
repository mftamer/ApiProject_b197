package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetstoreBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp(){
        String baserUrl ="https://petstore.swagger.io/v2";

        spec = new RequestSpecBuilder().setBaseUri(baserUrl).build();
    }
}
