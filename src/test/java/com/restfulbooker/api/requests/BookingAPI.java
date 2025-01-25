package com.restfulbooker.api.requests;

import com.restfulbooker.api.BaseAPI;
import com.restfulbooker.api.requests.util.BookingSearch;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BookingAPI extends BaseAPI {
    private static final String PATH = "/booking";

    public static Response getBookingIds(BookingSearch bookingSearch) {
        RequestSpecification request = given();

        if (bookingSearch.firstname() != null) {
            request.queryParam("firstname", bookingSearch.firstname());
        }
        if (bookingSearch.lastname() != null) {
            request.queryParam("lastname", bookingSearch.lastname());
        }
        if (bookingSearch.checkin() != null) {
            request.queryParam("checkin", bookingSearch.checkin());
        }
        if (bookingSearch.checkout() != null) {
            request.queryParam("checkout", bookingSearch.checkout());
        }

        return request
                .when()
                    .get(PATH);
    }
}
