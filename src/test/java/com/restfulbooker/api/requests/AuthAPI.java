package com.restfulbooker.api.requests;

import com.restfulbooker.api.requests.entities.Auth;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AuthAPI {
    public static final String PATH = "/auth";

    public static RequestSpecification createToken(String username, String password) {
        return createToken(new Auth(username, password));
    }

    public static RequestSpecification createToken(Auth body) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setContentType(ContentType.JSON);
        if (body != null) {
            builder.setBody(body);
        }

        return builder.build();
    }

    public static void setAuthHeader(RequestSpecification request) {
        request.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }
}
