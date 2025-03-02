package com.restfulbooker.api.tests;

import com.restfulbooker.api.util.ResponseTimeFilter;
import com.restfulbooker.api.TestBaseAPI;
import com.restfulbooker.api.requests.PingAPI;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPing extends TestBaseAPI {
    private static ResponseSpecification expectedPingResponse;

    @BeforeClass
    void buildExpectedPing() {
        expectedPingResponse = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.TEXT)
                .expectBody(equalTo("Created"))
                .build();
    }

    @Test
    void testPingIsWorking() {
        given(PingAPI.ping())
            .filter(new ResponseTimeFilter(1000))
        .when()
            .get(PingAPI.PATH)
        .then()
            .spec(expectedPingResponse);
    }
}
