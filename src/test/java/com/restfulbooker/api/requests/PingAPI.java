package com.restfulbooker.api.requests;

import com.restfulbooker.api.BaseAPI;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PingAPI extends BaseAPI {
    private static final String PATH = "/ping";

    public static Response ping() {
        return given()
                .get(PATH);
    }
}
