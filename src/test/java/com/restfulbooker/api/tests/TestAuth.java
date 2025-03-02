package com.restfulbooker.api.tests;

import com.restfulbooker.api.util.ResponseTimeFilter;
import com.restfulbooker.api.TestBaseAPI;
import com.restfulbooker.api.requests.AuthAPI;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestAuth extends TestBaseAPI {
    private static ResponseSpecification validCredentials;
    private static ResponseSpecification invalidCredentials;

    @BeforeClass
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
    @Parameters({"username", "password"})
    void tokenIsCreatedWithValidCredentials(String username, String password) {
        given(AuthAPI.createToken(username, password))
            .filter(new ResponseTimeFilter(1000))
        .when()
            .post(AuthAPI.PATH)
        .then()
            .spec(validCredentials);
    }

    @Test
    @Parameters({"username", "password"})
    void tokenIsNotCreatedWithInvalidCredentials() {
        given(AuthAPI.createToken("admin", "admin"))
            .filter(new ResponseTimeFilter(1000))
        .when()
            .post(AuthAPI.PATH)
        .then()
            .spec(invalidCredentials);
    }
}
