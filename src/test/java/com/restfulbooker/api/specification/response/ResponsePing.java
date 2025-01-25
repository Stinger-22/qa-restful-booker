package com.restfulbooker.api.specification.response;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class ResponsePing {
    public static ResponseSpecification expectedPing() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.TEXT.withCharset("utf-8"))
                .expectBody(equalTo("Created"))
                .build();
    }
}
