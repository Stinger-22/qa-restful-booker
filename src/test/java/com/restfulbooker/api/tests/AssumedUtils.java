package com.restfulbooker.api.tests;

import com.restfulbooker.api.requests.AuthAPI;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.restfulbooker.api.requests.util.Utilities.extractToken;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumedUtils {
    public static void setCookieToken(RequestSpecification request) {
        RequestSpecification auth = AuthAPI.createToken("admin", "password123");
        Response response = given(auth).post(AuthAPI.PATH);
        assumeTrue(response.statusCode() == 200);
        request.cookie("token", extractToken(response));
    }
}
