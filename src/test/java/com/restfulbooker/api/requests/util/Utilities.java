package com.restfulbooker.api.requests.util;

import com.restfulbooker.api.requests.AuthAPI;
import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.entities.BookingRequest;
import com.restfulbooker.api.requests.entities.BookingResponse;
import com.restfulbooker.api.requests.entities.Token;
import com.restfulbooker.api.requests.exceptions.AuthEndpointNotWorkingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static com.restfulbooker.api.requests.BookingAPI.createBooking;
import static io.restassured.RestAssured.given;

public class Utilities {
    public static String receiveCookieToken() {
        RequestSpecification auth = AuthAPI.createToken("admin", "password123");
        Response response = given(auth).post(AuthAPI.PATH);
        if (response.statusCode() != 200) {
            throw new AuthEndpointNotWorkingException("Received status code is not 200, but " + response.statusCode());
        }
        return extractToken(response);
    }

    public static String extractToken(Response response) {
        return response.body().as(Token.class).token();
    }

    public static void setCookieToken(RequestSpecification request, String token) {
        request.cookie("token", token);
    }

    public static String receiveAuthToken() {
        return "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    }

    public static void setAuthToken(RequestSpecification request) {
        request.header("Authorization", receiveAuthToken());
    }

    public static BookingResponse createDefaultBooking() {
        Response response = given(createBooking(BookingRequest.defaultBooking(), ContentType.JSON.toString(), null))
                .post("/booking");
        return response.body().as(BookingResponse.class);
    }

    public static void deleteBooking(int id) {
        String token = receiveCookieToken();
        RequestSpecification deleteBooking = given(BookingAPI.deleteBooking(id));
        setCookieToken(deleteBooking, token);
        given(deleteBooking).delete("/booking/{id}");
    }

    public static void deleteAllBookings() {
        RequestSpecification getIds = given(BookingAPI.getBookingIds());
        Response response = given(getIds).get("/booking");

        String token = receiveCookieToken();
        List<Integer> createdBookingsIds = response.then().extract().path("bookingid");
        for (int id : createdBookingsIds) {
            RequestSpecification deleteBooking = given(BookingAPI.deleteBooking(id));
            setCookieToken(deleteBooking, token);
            given(deleteBooking).delete("/booking/{id}");
        }
    }
}
