package com.restfulbooker.api.tests;

import com.restfulbooker.api.requests.PingAPI;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPing extends PingAPI {
    private static ResponseSpecification expectedPingResponse;

    @BeforeAll
    static void buildExpectedPing() {
        expectedPingResponse = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.TEXT)
                .expectBody(equalTo("Created"))
                .build();
    }

    @Test
    void testPingIsWorking() {
        given(ping())
        .when()
            .get(PATH)
        .then()
            .spec(expectedPingResponse);
    }
}
