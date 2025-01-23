package com.restfulbooker.api;

import com.restfulbooker.api.specification.response.ResponsePing;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestPing extends TestBase {
    private static final String PING_PATH = "ping/";

    @Test
    void testPingIsWorking() {
        ResponseSpecification expectedResponse = ResponsePing.expectedPing();

        given()
            .get(PING_PATH)
        .then()
            .spec(expectedResponse);
    }
}
