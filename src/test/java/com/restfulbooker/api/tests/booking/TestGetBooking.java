package com.restfulbooker.api.tests.booking;

import com.restfulbooker.api.TestBaseAPI;
import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.entities.BookingResponse;
import com.restfulbooker.api.requests.util.BookingSearch;
import com.restfulbooker.api.requests.util.Utilities;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestGetBooking extends TestBaseAPI {
//    TODO fix magic numbers
    private static List<Integer> createdBookingsIds;

    @BeforeClass
    static void setup() {
        List<BookingResponse> createdBookings = Utilities.createDefaultBookings();
        createdBookingsIds = createdBookings.stream().map(BookingResponse::bookingid).collect(Collectors.toList());
    }

    @AfterClass
    static void teardown() {
        Utilities.deleteAllBookings();
    }

    @Test
    void getAllBookings() {
        Response response = given(BookingAPI.getBookingIds())
                .when().get(BookingAPI.PATH);

        List<Integer> ids = response
                .then()
                    .assertThat().statusCode(200)
                .extract()
                    .path("bookingid");

        assertEquals(ids.size(), createdBookingsIds.size());
        assertTrue(ids.containsAll(createdBookingsIds));
        assertTrue(createdBookingsIds.containsAll(ids));
    }

    @Test
    void getBookingsFilteredByFirstname() {
        BookingSearch.Builder searchBy = new BookingSearch.Builder();
        searchBy.firstname("Gandalf");
        Response response = given(BookingAPI.getBookingIds(searchBy.build()))
                .when().get(BookingAPI.PATH);

        List<Integer> ids = response.then().assertThat().statusCode(200).extract().path("bookingid");

        assertEquals(ids.size(), 2);
        assertTrue(ids.containsAll(createdBookingsIds));
        assertTrue(createdBookingsIds.containsAll(ids));
    }

    @Test
    void getBookingsFilteredByLastname() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingsFilteredByCheckin() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingsFilteredByCheckout() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingsFilteredByAll() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingsFilteredByAllEmptyResult() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingByIdInJSON() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingByIdInXML() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getNonExistentBookingById() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingByIdInInvalidFormat() {
        throw new RuntimeException("Not implemented");
    }
}
