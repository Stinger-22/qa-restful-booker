package com.restfulbooker.api.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class PingAPI {
    public static final String PATH = "/ping";

    public static RequestSpecification ping() {
        return new RequestSpecBuilder().build();
    }
}
