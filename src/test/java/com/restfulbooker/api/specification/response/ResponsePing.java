package com.restfulbooker.api.specification.response;

import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;

public class ResponsePing {
    public static ResponseSpecification expectedPing() {
        return expect()
                .statusCode(201)
                .contentType(ContentType.TEXT.withCharset("utf-8"))
                .body(containsString("Created"));
    }
}
