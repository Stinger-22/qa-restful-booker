package com.restfulbooker.api.tests;

import com.restfulbooker.api.requests.AuthAPI;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestAuth extends AuthAPI {
    private static ResponseSpecification validCredentials;
    private static ResponseSpecification invalidCredentials;

    @BeforeAll
    static void buildExpectedResponses() {
        validCredentials = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        invalidCredentials = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    @Test
    void tokenIsCreatedWithValidCredentials() {
        given(createToken("admin", "password123"))
        .when()
            .post(PATH)
        .then()
            .spec(validCredentials);
    }

    @Test
    void tokenIsNotCreatedWithInvalidCredentials() {
        given(createToken("admin", "admin"))
        .when()
            .post(PATH)
        .then()
            .spec(invalidCredentials);
    }
}
