package com.restfulbooker.api;

import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.util.BookingSearch;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static com.restfulbooker.api.requests.BookingAPI.getBookingIds;
import static io.restassured.RestAssured.given;

/**
 * In this class RestAssured default behaviour is configured.
 * Each test class should extend this.
 */
public class BaseAPI {
    protected static final String BASE_URI = "http://localhost";
    protected static final int PORT = 3001;

    @BeforeAll
    static void setUpRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = PORT;

        // TODO add log4j 2
        // TODO setup logging in file (use filters)
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // TODO write log
        if (!checkWhetherInitialDBIsEmpty()) {
            throw new InitialDBStateIsNotEmptyException("Database not empty. API tests won't be executed.");
        }
    }

    private static boolean checkWhetherInitialDBIsEmpty() {
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
