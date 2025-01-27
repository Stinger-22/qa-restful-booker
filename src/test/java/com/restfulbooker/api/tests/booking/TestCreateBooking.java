package com.restfulbooker.api.tests.booking;

import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.entities.BookingDates;
import com.restfulbooker.api.requests.entities.BookingRequest;
import com.restfulbooker.api.requests.entities.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class TestCreateBooking extends BookingAPI {
    @AfterAll
    public static void teardown() {
//        TODO Delete created bookings
    }

    @Test
    void createBooking() {
        BookingRequest br = new BookingRequest
                ("Jim", "Brown", 111, true,
                new BookingDates(LocalDate.of(2018, 1, 1),
                                 LocalDate.of(2019, 1, 1)),
                "Breakfast");

        Response response = given(createBooking(br, ContentType.JSON.toString(), null))
        .when()
            .post(PATH);
        response.then()
                .statusCode(201);

        BookingResponse receivedBody = response.body().as(BookingResponse.class);
        assert receivedBody.booking().equals(br);

    }

    @ParameterizedTest
    void createInvalidBooking() {
        throw new RuntimeException("Not implemented");
    }
}
