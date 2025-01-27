package com.restfulbooker.api.requests;

import com.restfulbooker.api.BaseAPI;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class PingAPI extends BaseAPI {
    public static final String PATH = "/ping";

    public static RequestSpecification ping() {
        return new RequestSpecBuilder().build();
    }
}
