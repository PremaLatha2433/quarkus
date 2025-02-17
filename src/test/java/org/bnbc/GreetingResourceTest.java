package org.bnbc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello Prema How are you?"));
    }

    @Test
    void testGreetingEndPoint(){
        String uId = UUID.randomUUID().toString();
        given().pathParams("name",uId).when().
                get("/hello/greeting/{name}").
                then().
                statusCode(200)
                .body(is("Hello How are you? "+uId));
    }
}