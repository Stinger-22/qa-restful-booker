package com.restfulbooker.api.tests;

import com.restfulbooker.api.BaseAPI;
import com.restfulbooker.api.requests.BookingAPI;
import com.restfulbooker.api.requests.util.BookingSearch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class TestBooking extends BaseAPI {
    @Test
    void noBookingsAreExisting() {
        BookingSearch search = new BookingSearch.Builder().build();
        Response r = BookingAPI.getBookingIds(search);

        r.then()
            .statusCode(200)
            .contentType(ContentType.JSON.withCharset("utf-8"))
            .body("size()", is(0));
    }
}
