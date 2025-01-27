package com.restfulbooker.api.requests.util;

import com.restfulbooker.api.requests.entities.Token;
import io.restassured.response.Response;

public class Utilities {
    public static String extractToken(Response response) {
        return response.body().as(Token.class).token();
    }
}
