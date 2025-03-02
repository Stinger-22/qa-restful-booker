package com.restfulbooker.api.tests.booking;

import com.restfulbooker.api.TestBaseAPI;
import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.util.Utilities;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestDeleteBooking extends TestBaseAPI {
    private static final String PATH = "/booking/{id}";

    private static ResponseSpecification successfullyDeleted;

    @BeforeClass
    static void buildExpectedResponses() {
        successfullyDeleted = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @Test
    void deleteBookingWithCookieToken() {
        int id = Utilities.createDefaultBooking().bookingid();

        String token = Utilities.receiveCookieToken();
        RequestSpecification deleteBooking = given(BookingAPI.deleteBooking(id));
        Utilities.setCookieToken(deleteBooking, token);
        given(deleteBooking)
        .when()
            .delete(PATH)
        .then()
            .spec(successfullyDeleted);
    }

    @Test
    void deleteBookingWithAuthToken() {
        int id = Utilities.createDefaultBooking().bookingid();

        RequestSpecification deleteBooking = given(BookingAPI.deleteBooking(id));
        Utilities.setAuthToken(deleteBooking);
        given(deleteBooking)
        .when()
            .delete(PATH)
        .then()
            .spec(successfullyDeleted);
    }

    @Test
    void deleteBookingWithoutToken() {
        int id = Utilities.createDefaultBooking().bookingid();

        given(BookingAPI.deleteBooking(id))
        .when()
            .delete(PATH)
        .then()
             .statusCode(403);

        Utilities.deleteBooking(id);
    }

    @Test
    void deleteNonExistingBooking() {
        given(BookingAPI.deleteBooking(1))
        .when()
            .delete(PATH)
        .then()
            .statusCode(404);
    }
}
