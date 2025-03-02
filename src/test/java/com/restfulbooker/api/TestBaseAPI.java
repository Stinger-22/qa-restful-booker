package com.restfulbooker.api;

import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.util.BookingSearch;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.List;

import static com.restfulbooker.api.requests.BookingAPI.getBookingIds;
import static io.restassured.RestAssured.given;

/**
 * In this class RestAssured default behaviour is configured.
 * Each test class should extend this.
 */
public class TestBaseAPI {
    protected static final String BASE_URI = "http://localhost";
    protected static final int PORT = 3001;

    @BeforeSuite
    public void setUpRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        if (!checkWhetherInitialDBIsEmpty()) {
            throw new InitialDBStateIsNotEmptyException("Database not empty. API tests won't be executed.");
        }
    }

    private boolean checkWhetherInitialDBIsEmpty() {
        BookingSearch search = new BookingSearch.Builder().build();

        Response response = given(getBookingIds(search))
        .when()
            .get(BookingAPI.PATH);
        response.then()
            .statusCode(200)
            .contentType(ContentType.JSON);

        return response.body().as(List.class).isEmpty();
    }

    private static class InitialDBStateIsNotEmptyException extends RuntimeException {
        InitialDBStateIsNotEmptyException(String message) {
            super(message);
        }
    }
}
