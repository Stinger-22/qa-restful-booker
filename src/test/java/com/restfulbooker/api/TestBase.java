package com.restfulbooker.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

/**
 * In this class RestAssured default variables are configured.
 * Each test class should extend this.
 */
public class TestBase {
    protected static final String BASE_URI = "http://localhost";
    protected static final int PORT = 3001;

    @BeforeAll
    static void setUpRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;
    }
}
