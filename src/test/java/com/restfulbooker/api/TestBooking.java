package com.restfulbooker.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestBooking extends TestBase {
    private static final String BOOKING_PATH = "booking/";

    @Test
    void noBookingsAreExisting() {
        given()
                .get(BOOKING_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.withCharset("utf-8"))
                .body("size()", is(0));
    }
}
