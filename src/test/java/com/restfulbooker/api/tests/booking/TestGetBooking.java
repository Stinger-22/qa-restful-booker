package com.restfulbooker.api.tests.booking;

import com.restfulbooker.api.requests.BookingAPI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestGetBooking extends BookingAPI {
    @BeforeAll
    static void setup() {
//      TODO Create bookings. Calculate minimum number of required bookings for tests
    }

    @AfterAll
    static void teardown() {
//        TODO Delete created bookings
    }

    @Test
    void getAllBookings() {
        throw new RuntimeException("Not implemented");
    }

    @Test
    void getBookingsFilteredByFirstname() {
        throw new RuntimeException("Not implemented");
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
